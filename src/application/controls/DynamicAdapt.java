package application.controls;

import java.awt.GridLayout;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class DynamicAdapt {
	
	private Pane MainContainer;
	
	public DynamicAdapt(Pane MainContainer) {
		this.MainContainer = MainContainer;
	}
	
	public void SetDynamicSize(Control element, double Xcoef, double Ycoef)
	{
		if (Xcoef < 0 || Ycoef < 0) return;
		element.prefWidthProperty().bind(MainContainer.widthProperty().multiply(Xcoef));
		element.prefHeightProperty().bind(MainContainer.heightProperty().multiply(Ycoef));
	}
	
	public void SetDynamicLocation(Node element, Pane container, PointCoef point) {
		element.layoutXProperty().bind(container.widthProperty().multiply(point.xCoef));
		element.layoutYProperty().bind(container.heightProperty().multiply(point.yCoef));
	}
	
	public void SetDynamicFont(Control element) {
		SetDynamicFont(element, 0.45);
	}
	
	public void SetDynamicSpacing(VBox box, double Coef) {
		box.spacingProperty().bind(MainContainer.heightProperty().multiply(Coef));
	}
	
	public void SetDynamicFont(Control element, double FontCoef)
	{
		
		ChangeListener<Number> stageSizeListener = new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				String text = element.getStyle();
				int ind = text.indexOf("-fx-font-size:");
				String newText;
				if (ind > 0)
				{
					newText = text.subSequence(0, ind).toString() + text.subSequence(ind, ind+14).toString()
							+ String.format("%dpx", (int)(FontCoef * element.getHeight()));
				}
				else {
					newText = text + String.format(" -fx-font-size: %dpx", (int)(FontCoef * element.getHeight()));
				}
				element.setStyle(newText);
			}
		};
		
		element.widthProperty().addListener(stageSizeListener);
		element.heightProperty().addListener(stageSizeListener);
	}
	
	public void SetDynamicPadding(VBox vbox, Control element, InsetsCoef coef) {
		
		ChangeListener<Number> stageSizeListener = new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				double up = vbox.getHeight() * coef.UpCoef;
				double down = vbox.getHeight() * coef.DownCoef;
				double left = vbox.getHeight() * coef.LeftCoef;
				double right = vbox.getHeight() * coef.RightCoef;
				Insets insets = new Insets(up, right, down, left);
				vbox.setMargin(element, insets);
			}
		};
		
		vbox.widthProperty().addListener(stageSizeListener);
		vbox.heightProperty().addListener(stageSizeListener);
	}
	
	public void SetDynamicPadding(GridPane grid, Control element, InsetsCoef coef) {
		privatePaddingSet(grid, element, coef, true);
		
	}
	
	private void privatePaddingSet(Pane pane, Control element, InsetsCoef coef, boolean grid) {
		ChangeListener<Number> stageSizeListener = new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				double up = pane.getHeight() * coef.UpCoef;
				double down = pane.getHeight() * coef.DownCoef;
				double left = pane.getHeight() * coef.LeftCoef;
				double right = pane.getHeight() * coef.RightCoef;
				Insets insets = new Insets(up, right, down, left);
				
				if (grid)	((GridPane)pane).setMargin(element, insets);
				else ((VBox)pane).setMargin(element, insets);
			}
		};
		
		pane.widthProperty().addListener(stageSizeListener);
		pane.heightProperty().addListener(stageSizeListener);
	}
	
	
	public void SetDynamicRadius(Circle circle, double radiusCoef, double windowCoef) {
		
		ChangeListener<Number> stageSizeListener = new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				double size;
				if (MainContainer.getWidth() > MainContainer.getHeight() * windowCoef) size = MainContainer.getHeight() * windowCoef;
				else size = MainContainer.getWidth();
				circle.setRadius(size * radiusCoef);
			}
		};
		
		MainContainer.widthProperty().addListener(stageSizeListener);
		MainContainer.heightProperty().addListener(stageSizeListener);
	}
	
	public void SetDynamicRadius(Circle circle, double radiusCoef) {
		SetDynamicRadius(circle, radiusCoef, 1);
	}
	
	public static void SetStageSize(Stage stage, ActionEvent event) {
		double xWindow = ((Node)event.getSource()).getScene().getWidth();
		double yWindow = ((Node)event.getSource()).getScene().getHeight();
		SetStageSize(stage, xWindow, yWindow);
	}
	
	public static void SetStageSize(Stage stage, double width, double height) {
		stage.setWidth(width);
		stage.setHeight(height);
	}
}
