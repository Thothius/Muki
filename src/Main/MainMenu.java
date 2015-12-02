package Main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


// For JavaFx to work we need to extend the class
public class MainMenu extends Application {

	// Button, stage and VBox declarations
	Button authorButton;
	Button viewPlansButton;
	Button getInfoButton;
	Button closeButton;
	Stage window;
	VBox layout;

	// Main method
	public static void main(String[] args) {
		launch(args);
	}

	
	
	
	// Stage, title, elements and button display 
	@Override 
	public void start(Stage primaryStage) throws Exception {

		buttonSetup();
		window = primaryStage;
		window.setOnCloseRequest(e -> closeProgram());
		window.setTitle("Muki 0.1a");
		VBox layout = new VBox(20);
		layout.getChildren().addAll(viewPlansButton,getInfoButton,authorButton,closeButton);
		layout.setAlignment(Pos.CENTER);
		Scene window = new Scene(layout,300,500);
		primaryStage.setScene(window);
		primaryStage.show();

	}

	// Button Setup
	public void buttonSetup() {

		//Author Button
		authorButton = new Button();
		authorButton.setText("Display author");
		authorButton.setOnAction(e -> AuthorBox.display("Author Page", "Kristjan Saaremets C11"));


		//Play Button
		viewPlansButton = new Button();
		viewPlansButton.setText("My Plans");
		viewPlansButton.setOnAction(e -> PlansPage.display("My Plans Page", "You have no plans yet"));

		//Info Button
		getInfoButton = new Button();
		getInfoButton.setText("Get Info");
		getInfoButton.setOnAction(e -> InfoPage.display("Info Page", "Give me some info!"));

		//Exit Button
		closeButton = new Button();
		closeButton.setText("Exit");
		closeButton.setOnAction(e -> closeProgram());
	}

	// Program closing
	public void closeProgram(){
		Boolean confirmer = ExitBox.display("Exit", "Confirm exit");
		if (confirmer) {
			window.close();
		}
	}

}
