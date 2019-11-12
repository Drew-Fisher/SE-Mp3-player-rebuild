package mpeRecreation;

import javafx.collections.ObservableList;
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

public class addWindow {
	public static String ans;
	
	public static String display(ObservableList items) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Enter PlayList Name");
		window.setMinWidth(280);
		
		Label instruction = new Label("Select song to add to PlayList");
		Label error = new Label();
		
		ListView loS = new ListView();
		loS.setItems(items);
		
		VBox layout = new VBox();
		HBox Buttons = new HBox();
		
		Button Add = new Button("Add");
		Button Cancel = new Button("Cancel");
		
		Add.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(loS.getSelectionModel().getSelectedItem() == null) {
					error.setText("you must select a song before you can add it to your playlist");
				}
				else {
					ans = (String)loS.getSelectionModel().getSelectedItem();
					window.close();
				}
				
			}
			
		});
		
		Cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ans = null;
				window.close();
			}
			
		});
		Buttons.getChildren().addAll(Add,Cancel);
		layout.getChildren().addAll(instruction,loS,error,Buttons);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		return ans;
	}
}
