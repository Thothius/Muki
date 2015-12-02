package Main;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class InfoPage {
	public static void display(String title, String messege){
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(400);
		window.setMinHeight(400);
		
		Label label1 = new Label();
		label1.setText(messege);
		//label1.setAlignment(Pos.TOP_CENTER);
		
		Button closeButton = new Button("Main Menu");
		closeButton.setOnAction( e-> window.close());
		
		VBox layout = new VBox(20);
		layout.getChildren().addAll(label1, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}
