package Main;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.*;


public class AboutBox {
	public static void display(){										// A method to display the About pop-up
		
		Stage window = new Stage();										// Creates a new stage
		window.setTitle("About Muki");									// Sets the title of the stage
		window.setMinWidth(400);
		window.setMinHeight(400);

		Label authorLabel = new Label("Author: Kristjan Saaremets C11");	// Creates a new label node
		Text about = new Text("Muki is a prototype ToDoList application.");	// Crates a new text node
		
		VBox imgLayout = new VBox();
		imgLayout.setAlignment(Pos.TOP_CENTER);
		Image img = new Image("titleAbout.png");	// Loads a new image
		ImageView imgPainter = new ImageView(img);		// Creates a node to paint images		
		imgLayout.getChildren().add(imgPainter);
		
		

		Button closeButton = new Button("Back");							// Creates a new button
		closeButton.setOnAction( e-> window.close());						// Closes the window on button click
		closeButton.setAlignment(Pos.BOTTOM_CENTER);						// Positions the button to the bottom
		VBox layout = new VBox(25);											// Creates a new VBox layout
		layout.getChildren().addAll(imgLayout,about,authorLabel, closeButton);		// Adds all the nodes to the layout
		layout.setAlignment(Pos.CENTER);									// Positions it to the center
		Scene scene = new Scene(layout);									// Creates a new scene with the layout
		scene.getStylesheets().add("Muki.css");							// Adds CSS
		window.setScene(scene);												// Adds the scene to the stage
		window.showAndWait();												// Restricts clicking outside the stage
	}
}
