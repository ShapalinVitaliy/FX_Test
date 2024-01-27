package application;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.Event;
import java.awt.Point;
import java.io.IOException;

import application.controls.DynamicAdapt;
import application.controls.InsetsCoef;
import application.controls.PointCoef;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class CircleStage {
	
	private Parent prevRoot;
	
	CircleMove circleMove = new CircleMove(new PointCoef(0.15, 0.225));
	
	private PointCoef circleLocation = new PointCoef(0.5, 0.5);
	private DynamicAdapt mainAdapt;
	
	
	@FXML
	private Button leftButton;
	@FXML
	private Button rightButton;
	@FXML
	private Button upButton;
	@FXML
	private Button downButton;
	@FXML
	private Button backButton;
	@FXML
	private Circle circleToy;
	@FXML
	private Pane circleContainer;
	@FXML
	private GridPane mainContainer;
	
	
	@FXML
    protected void initialize() {
		mainAdapt = new DynamicAdapt(mainContainer);
		
		mainAdapt.SetDynamicLocation(circleToy, circleContainer, circleLocation);
		
		mainAdapt.SetDynamicSize(leftButton, 0.08, 0.06);
		mainAdapt.SetDynamicFont(leftButton);
		
		mainAdapt.SetDynamicSize(rightButton, 0.08, 0.06);
		mainAdapt.SetDynamicFont(rightButton);
		
		mainAdapt.SetDynamicSize(upButton, 0.08, 0.06);
		mainAdapt.SetDynamicFont(upButton);
		
		mainAdapt.SetDynamicSize(downButton, 0.08, 0.06);
		mainAdapt.SetDynamicFont(downButton);
		
		mainAdapt.SetDynamicSize(backButton, 0.025, 0.035);
		mainAdapt.SetDynamicFont(backButton);

		mainAdapt.SetDynamicRadius(circleToy, 0.12, 1.5);
	}
	
	
	@FXML
	protected void UpClick()
	{
		circleMove.Step(circleLocation, Direction.UP);
		mainAdapt.SetDynamicLocation(circleToy, circleContainer, circleLocation);
	}
	
	@FXML
	protected void DownClick()
	{
		circleMove.Step(circleLocation, Direction.DOWN);
		mainAdapt.SetDynamicLocation(circleToy, circleContainer, circleLocation);
	}
	
	@FXML
	protected void LeftClick()
	{
		circleMove.Step(circleLocation, Direction.LEFT);
		mainAdapt.SetDynamicLocation(circleToy, circleContainer, circleLocation);
	}
	
	@FXML
	protected void RightClick()
	{
		circleMove.Step(circleLocation, Direction.RIGHT);
		mainAdapt.SetDynamicLocation(circleToy, circleContainer, circleLocation);
	}
	
	@FXML
	protected void backClick(ActionEvent event)
	{
		if (prevRoot == null) return;
		
		Scene scene = (Scene) ((Node)event.getSource()).getScene();
		scene.setRoot(prevRoot);
	}
	
	
	
	public void SetPrevRoot(Parent prevRoot)
	{
		this.prevRoot = prevRoot;
	}
	
	private class CircleMove {
		private PointCoef HighLimit;
		private PointCoef LowLimit;
		
		private double stepX;
		private double stepY;
		
		private double stepPercent = 5;
		
		public void setStepPercent (double stepPercent){
			this.stepPercent = stepPercent;
			updateStep();
		}
		
		public CircleMove(PointCoef LowLimit) {
			this.LowLimit = LowLimit;
			this.HighLimit = new PointCoef(1 - LowLimit.xCoef, 1 - LowLimit.yCoef);
			updateStep();
		}
		
		public CircleMove(PointCoef LowLimit, double stepPercent) {
			this.LowLimit = LowLimit;
			this.HighLimit = new PointCoef(1 - LowLimit.xCoef, 1 - LowLimit.yCoef);
			this.stepPercent = stepPercent;
			updateStep();
		}
		
		public CircleMove(PointCoef HighLimit, PointCoef LowLimit) {
			this.HighLimit = HighLimit;
			this.LowLimit = LowLimit;
			
			updateStep();
		}
		
		public CircleMove(PointCoef HighLimit, PointCoef LowLimit, double stepPercent) {
			this.HighLimit = HighLimit;
			this.LowLimit = LowLimit;
			this.stepPercent = stepPercent;
			
			updateStep();
		}
		
		private void updateStep() {
			this.stepX = (HighLimit.xCoef - LowLimit.yCoef) / 100 * this.stepPercent;
			this.stepY = (HighLimit.yCoef - LowLimit.xCoef) / 100 * this.stepPercent;
		}
		
		public void Step(PointCoef step, Direction direction) {
			switch (direction) {
				case UP :
					if (step.yCoef < LowLimit.yCoef) return;
					step.yCoef -= stepY;
					break;
				case DOWN:
					if (step.yCoef > HighLimit.yCoef) return;
					step.yCoef += stepY;
					break;
				case LEFT:
					if (step.xCoef < LowLimit.xCoef) return;
					step.xCoef -= stepX;
					break;
				case RIGHT:
					if (step.xCoef > HighLimit.xCoef) return;
					step.xCoef += stepX;
			}
		}
		
		
	}
	
	private enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
}
