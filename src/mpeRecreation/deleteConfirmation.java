package mpeRecreation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class deleteConfirmation {
	private static boolean resault =  false;
	
	public static boolean display() {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Enter PlayList Name");
		window.setMinWidth(280);
		
		Label instruction = new Label("Are you sure you want to delete this list?");
		Label error = new Label();
		
		VBox layout = new VBox();
		HBox Buttons = new HBox();
		
		Button Add = new Button("Delete");
		Button Cancel = new Button("Cancel");
		
		Add.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				resault = true;
				window.close();
			}
			
		});
		
		Cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				window.close();
			}
			
		});
		Buttons.getChildren().addAll(Add,Cancel);
		layout.getChildren().addAll(instruction,error,Buttons);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		return resault;
	}
}
