package Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


// The HelpBox class is used to display an image with instructions and a Back button. Nothing is returned.

public class HelpBox {

public static void display(){										// A method to display the About pop-up
		
		Stage window = new Stage();										// Creates a new stage
		window.setTitle("How To Plan");										// Sets the title of the stage
		window.setMinWidth(500);
		window.setMinHeight(550);

		VBox imgLayout = new VBox();
		imgLayout.setAlignment(Pos.TOP_CENTER);
		Image helpImg = new Image("help.png");
		ImageView imgPainter = new ImageView(helpImg);		// Creates a node to paint images		
		imgLayout.getChildren().add(imgPainter);
		
		

		Button closeButton = new Button("Back");							// Creates a new button
		closeButton.setOnAction( e-> window.close());						// Closes the window on button click
		//closeButton.setAlignment(Pos.TOP_CENTER);						// Positions the button to the bottom
		
		
		VBox layout = new VBox(25);											// Creates a new VBox layout
		layout.setPadding(new Insets(25,25,25,25));
		layout.getChildren().addAll(imgLayout,closeButton);					// Adds all the nodes to the layout
		layout.setAlignment(Pos.TOP_CENTER);									// Positions it to the center
		Scene scene = new Scene(layout);									// Creates a new scene with the layout
		scene.getStylesheets().add("Muki.css");								// Adds CSS
		window.setScene(scene);												// Adds the scene to the stage
		window.showAndWait();												// Restricts clicking outside the stage
	}
	
	
	
}
