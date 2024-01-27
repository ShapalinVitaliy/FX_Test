package application;
	

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;


public class Main extends Application {
	
	
	public Circle toy;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/Menu.fxml"));
			Parent root = loader.load();
			
			Scene scene = new Scene(root, 640, 400);
			
			primaryStage.setTitle("Test");
		
			primaryStage.setScene(scene);
			primaryStage.setMinWidth(400);
			primaryStage.setMinHeight(150);
			primaryStage.setFullScreenExitHint("");
			primaryStage.setFullScreen(true);
			primaryStage.show();
			
			
			
			Menu menu = loader.getController();
			menu.SetStage(primaryStage);// = primaryStage;
			
			
			
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent event) {
	                switch (event.getCode()) {
	                    case F12:    
	                    	if (primaryStage.isFullScreen()) primaryStage.setFullScreen(false);
	                    	else primaryStage.setFullScreen(true);
	                    	break;
	                }
	            }
	        });
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
