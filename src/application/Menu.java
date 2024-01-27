package application;
import java.io.IOException;

import application.controls.DynamicAdapt;
import application.controls.InsetsCoef;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class Menu {

	private Stage stage;
	
	@FXML
	private GridPane gridMain;
	@FXML
	private Button playButton;
	@FXML
	private Button exitButton;
	@FXML
	private Label labelTitle;
	@FXML
	private VBox buttonsContainer;
	

	@FXML
    protected void initialize() {
		DynamicAdapt resize = new DynamicAdapt(gridMain);
	
		
		
		resize.SetDynamicSize(playButton, 0.125, 0.125);
		resize.SetDynamicFont(playButton);
		
		resize.SetDynamicSize(exitButton, 0.125, 0.125);
		resize.SetDynamicFont(exitButton);
		
		resize.SetDynamicSize(labelTitle, 0.33, 0.16);
		resize.SetDynamicFont(labelTitle);
		
		resize.SetDynamicSpacing(buttonsContainer, 0.20);
	}
	
	@FXML
	private void onClickExit()
	{
		System.exit(0);
	}
	
	@FXML
	private void onPlayClick(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/circleStage.fxml"));
        Parent root = loader.load();
        
        CircleStage controller = loader.getController();
        Scene scene = ((Node)event.getSource()).getScene();
        controller.SetPrevRoot(scene.getRoot());

        scene.setRoot(root);
	}
	
	public void SetStage(Stage stage) {
		this.stage = stage;
	}
	
}
