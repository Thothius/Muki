package Main;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
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
		window.setMinHeight(50);
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		
		
		
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

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		return confirmer;
	}
}
