package mpeRecreation;

import javafx.application.Application;
import javafx.stage.Stage;


import java.io.File;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class Main extends Application{
	private MediaPlayer player;
	
	

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("ui.fxml"));
		Scene scene = new Scene(root);
		arg0.setTitle("test");
		FileManager.buildApp();
		arg0.setScene(scene);
		arg0.show();
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	
	
	
	
	
	


}
