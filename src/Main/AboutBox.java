package Main;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.geometry.*;


public class AboutBox {
	public static void display(){
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("About Muki");
		window.setMinWidth(400);
		window.setMinHeight(400);

		Label authorLabel = new Label();
		authorLabel.setText("Author: Kristjan Saaremets C11");
		authorLabel.setAlignment(Pos.BOTTOM_RIGHT);
		
		
		Text about = new Text();
		about.setText("Muki is a prototype ToDoList application.");

		Button closeButton = new Button("Back");
		closeButton.setOnAction( e-> window.close());
		closeButton.setAlignment(Pos.BOTTOM_CENTER);
		
		VBox layout = new VBox(60);
		
		layout.getChildren().addAll(about,authorLabel, closeButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		scene.getStylesheets().add("/Muki.css");

		window.setScene(scene);
		window.showAndWait();
	}
}
