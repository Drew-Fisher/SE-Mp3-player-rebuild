package mpeRecreation;

import javafx.scene.control.Label;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class makeListWindoe {
	static playList newP; 
	public static playList display() {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Enter PlayList Name");
		window.setMinWidth(280);
		
		TextField text = new TextField();
		
		
		
		Label title = new Label();
		title.setText("Enter Name");
		
		Label error = new Label();
		
		Button enter = new Button("submit");
		enter.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String entry = text.getText();
				if(entry.equals(null) || entry.equals("")) {
					error.setText("The name you entered is invalid");
				}
				else {
					try {
						if(FileManager.checkplayList(entry)) {
							newP = new playList(entry);
							FileManager.savePlayList(newP);
							window.close();
						}
						else {
							error.setText("The name you entered is invalid");
						}
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
		
		VBox layout = new VBox();
		layout.getChildren().addAll(title,text,enter,error);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		return newP;
	}
}
