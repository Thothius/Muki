package Main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


// For JavaFx to work we need to extend the class
public class MainWindow extends Application {

	// Button, stage and VBox declarations
	Button authorButton;
	Button viewPlansButton;
	Button getInfoButton;
	Button closeButton;
	Stage window;
	VBox layout;
	Boolean confirmer;
	MenuBar mainMenu;




	// Main method
	public static void main(String[] args) {
		launch(args);
	}




	// Stage, title, elements and button display 
	@Override 
	public void start(Stage primaryStage) throws Exception {

		buttonSetup();


		window = primaryStage;
		window.setOnCloseRequest(e -> {
			//Tells the program that we can close the program ourselves, manually
			e.consume();
			closeProgram();
		});
		window.setTitle("Muki 0.1a");



		//Main Menu Setup and children


		// Main Menu --> Plans
		Menu plansMenu = new Menu("Plans");

		MenuItem newPlan = new MenuItem("New Plan");
		newPlan.setOnAction(e -> AuthorBox.display("Author Page", "Kristjan Saaremets C11"));

		MenuItem viewPlans = new MenuItem("View Plans");
		viewPlans.setOnAction(e -> AuthorBox.display("Author Page", "Kristjan Saaremets C11"));

		SeparatorMenuItem seperator = new SeparatorMenuItem();

		MenuItem exitApp = new MenuItem("Exit");
		exitApp.setOnAction(e -> closeProgram());
		
		//Gets all the elements for the Plans Menu
		plansMenu.getItems().addAll(newPlan,viewPlans,seperator,exitApp);

		// Main Menu --> Help
		Menu helpMenu = new Menu("Help");

		MenuItem howTo = new MenuItem("How To Plan");
		howTo.setOnAction(e -> AuthorBox.display("Author Page", "Kristjan Saaremets C11"));
		
		MenuItem aboutMuki = new MenuItem("About Muki");
		aboutMuki.setOnAction(e -> AuthorBox.display("Author Page", "Kristjan Saaremets C11"));

		helpMenu.getItems().addAll(howTo,aboutMuki);

		// Main Menu Bar
		MenuBar mainMenu = new MenuBar();
		mainMenu.getMenus().addAll(plansMenu,helpMenu);

		BorderPane layout = new BorderPane();

		layout.setTop(mainMenu);
		//layout.setBottom(authorButton);


		Scene window = new Scene(layout,400,400);
		window.getStylesheets().add("/Muki.css");
		primaryStage.setScene(window);
		primaryStage.show();

	}	
	// Main Menu Setup






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
		getInfoButton.setOnAction(e -> PlansPage.display("My Plans Page", "You have no plans yet"));

		//Exit Button
		closeButton = new Button();
		closeButton.setText("Exit");
		closeButton.setOnAction(e -> closeProgram());
	}

	// Program closing
	public void closeProgram(){
		Boolean confirmer = ExitBox.display();
		if (confirmer) {
			window.close();
		}
	}

}
