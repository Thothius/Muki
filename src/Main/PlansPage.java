package Main;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class PlansPage {
	public static void display(String title, String name){
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(400);
		window.setMinHeight(400);

		Label authorLabel = new Label();
		authorLabel.setText(name);
		Button closeButton = new Button("Main Menu");
		closeButton.setOnAction( e-> window.close());

		VBox layout = new VBox(20);
		layout.getChildren().addAll(authorLabel, closeButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
	
}
