package Main;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class ExitBox {
	
	static boolean confirmer;
	
	public static boolean display(String title, String name){
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(400);
		window.setMinHeight(200);

		Label label = new Label();
		label.setText(name);

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

		VBox layout = new VBox(20);
		layout.getChildren().addAll(label, yesButton, noButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		return confirmer;
	}
}
