package Main;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.geometry.*;


public class ExitBox {
	
	public static boolean confirmer;
	
	public static boolean display(){
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		String title = "Exit?";
		window.setTitle(title);
		window.setMinWidth(200);
		window.setMinHeight(200);
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		Text confirmText = new Text("Are you sure?");
		
		
		
		// The Yes and No button in the Exit window
		yesButton.setOnAction( e-> {
			confirmer = true;
			window.close();
		});
		
		noButton.setOnAction( e-> {
			confirmer = false;
			window.close();
		});

		
		
		
		HBox layout = new HBox(20);
		layout.getChildren().addAll(yesButton, noButton);
		layout.setAlignment(Pos.CENTER);

		VBox exitLayout = new VBox(20);
		exitLayout.setAlignment(Pos.CENTER);
		exitLayout.getChildren().addAll(confirmText,layout);
		
		Scene scene = new Scene(exitLayout);
		scene.getStylesheets().add("/Muki.css");
		window.setScene(scene);
		window.showAndWait();
		return confirmer;
	}
}
