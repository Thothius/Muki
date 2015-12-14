package Main;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.geometry.*;

// The ExitBox class is used to display a pop-up window when the user wants to close the application. The users choice is returned.


public class ExitBox {							// A method to display a pop-up stage and ask the user if they want to close the application

	public static boolean confirmExit;						// Boolean to store the answer

	public static boolean display(){						// A method to display the pop-up

		Stage window = new Stage();							// Creates a stage for the pop-up
		//window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Exit Application");				// Sets the title of the stage
		window.setMinWidth(200);							// Sets minimul width
		window.setMinHeight(200);							// Sets minimum height
		Button yesButton = new Button("Yes");				// A YES button
		Button noButton = new Button("No");					// A NO button
		Text confirmText = new Text("Are you sure?");		// Creates a text node for info


		yesButton.setOnAction( e-> {						// Invokes an action on the YES button
			confirmExit = true;								// Sets the boolean value to true
			window.close();									// Closes the pop-up window
		});

		noButton.setOnAction( e-> {							// Invokes an action on the NO button
			confirmExit = false;							// Sets the boolean value to false
			window.close();									// Closes the pop-up window
		});


		HBox buttonLayout = new HBox(20);							// Creates a new HBox layout
		buttonLayout.getChildren().addAll(yesButton, noButton);		// Adds the button nodes to the layout
		buttonLayout.setAlignment(Pos.CENTER);						// Centers the layout

		VBox exitLayout = new VBox(20);								// Creates a new VBox layout
		exitLayout.setAlignment(Pos.CENTER);						// Centers the layout
		exitLayout.getChildren().addAll(confirmText,buttonLayout);	// Adds the info text node and button layout node 

		Scene scene = new Scene(exitLayout);						// Creates a new scene with the final layout
		scene.getStylesheets().add("Muki.css");						// Adds CSS to the scene
		window.setScene(scene);										// Adds the scene to the stage
		window.showAndWait();										// Restricts any clicks outside the stage
		return confirmExit;											// Returns the choice of the user
	}
}
