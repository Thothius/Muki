package Main;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlansList extends Application {

	Button authorButton;
	Button viewPlansButton;
	Button getInfoButton;
	Button closeButton;
	Button refreshButton;
	Stage window;
	VBox layout;
	Boolean confirmer;
	MenuBar mainMenu;

	// Main method
	public static void main(String[] args) {
		launch(args);
	}


	@Override 
	public void start(Stage primaryStage) throws Exception {

		
		buttonSetup();								// Creates the buttons
		window = primaryStage;						// Sets the primary stage
		window.setOnCloseRequest(e -> { 			// Close the stage request event
			e.consume();							// Consumes the close request 
			closeApplication();						// Invokes the application closing method
		});


		window.setTitle("Muki");				// Sets the title of the window
		BorderPane layout = new BorderPane();		// Adds BorderPane style layout


		layout.setTop(mainMenuBar()); 									// Sets the Main Menu Bar to top
		layout.setCenter(addPlansList()); 								// Sets the Plans List to the center
		layout.setBottom(refreshButton); 								// Sets the Refresh button to the bottom
		BorderPane.setAlignment(addPlansList(), Pos.BASELINE_CENTER); 	// Positions the Plans List in the center


		Scene window = new Scene(layout,350,600);	// Creates a new scene with BorderPane
		window.getStylesheets().add("/Muki.css"); 	// CSS Stylesheet path
		primaryStage.setScene(window); 				// Adds the Scene to the Stage
		primaryStage.show();						// Makes the stage visible

	}

	public MenuBar mainMenuBar(){					// Main Menu setup


		Menu plansMenu = new Menu("Plans");													// Creates a new Menu item called Plans
		MenuItem newPlan = new MenuItem("New Plan");										// Creates a menu item called New Plan
		newPlan.setOnAction(e -> NewPlanBox.display());										// Sets the action to New Plan to display the Plan Page
		MenuItem viewPlans = new MenuItem("Edit Plans");									// Creates a menu item called Edit PLans
		MenuItem statistics = new MenuItem("Statistics");									// Creates a menu item called Statistics
		SeparatorMenuItem separatorPlans = new SeparatorMenuItem();							// Creates a seperator for the Plans Menu
		MenuItem exitApp = new MenuItem("Exit");											// Creates a menu item called Exit
		exitApp.setOnAction(e -> closeApplication());										// Sets the action to Exit to run the closing method
		plansMenu.getItems().addAll(newPlan,viewPlans,separatorPlans,statistics,exitApp); 	// Adds all the creates menu items to the Plans Menu




		Menu helpMenu = new Menu("Help");

		// Help Menu Sub items
		// (How To Plan; About Muki)

		MenuItem howTo = new MenuItem("How To Plan");
		//howTo.setOnAction(e -> AuthorBox.display("Author Page", "Kristjan Saaremets C11"));

		MenuItem aboutMuki = new MenuItem("About Muki");
		aboutMuki.setOnAction(e -> AboutBox.display());

		SeparatorMenuItem seperatorHelp = new SeparatorMenuItem();


		// Add all the sub-menu items to Help Menu
		helpMenu.getItems().addAll(howTo,seperatorHelp, aboutMuki);


		// ****Main Menu Bar****
		MenuBar mainMenu = new MenuBar();

		// Add all the created menus items to the Main Menu Bar
		mainMenu.getMenus().addAll(plansMenu,helpMenu);

		return mainMenu;
	}

	public VBox addPlansList() {					// Plans List setup

		VBox planList = new VBox();				// Creates a new VBox node
		Text plan = new Text(InputEater.Update());
		System.out.println(plan.getText());// Adds a text node
		
		
		
		//first.textProperty()
		planList.setSpacing(20);				// Sets layout item spacing
		planList.setAlignment(Pos.TOP_CENTER);	// Positions the layout to center top
		
		


		VBox listImage = new VBox();					// A VBox node for Plans List image
		Image img = new Image("titlePlansList.png");	// Loads a new image
		ImageView imgPainter = new ImageView(img);		// Creates a node to paint images		
		listImage.setAlignment(Pos.CENTER);				// Alignes the image to the center
		listImage.getChildren().addAll(imgPainter);		// Adds the image to the VBox node


		planList.getChildren().addAll(listImage, plan);	// Adds Image node and text node to the plansList node


		return planList;	// Return the planList node					
	}

	public void buttonSetup() {						// Button Setup


		//Author Button, displays the author page
		authorButton = new Button();
		authorButton.setText("Display author");
		//authorButton.setOnAction(e -> AuthorBox.display("Author Page", "Kristjan Saaremets C11"));

		//Play Button
		viewPlansButton = new Button();
		viewPlansButton.setText("My Plans");
		viewPlansButton.setOnAction(e -> NewPlanBox.display());

		//Info Button
		getInfoButton = new Button();
		getInfoButton.setText("Get Info");
		getInfoButton.setOnAction(e -> NewPlanBox.display());

		//Exit Button, closes the program
		closeButton = new Button();
		closeButton.setText("Exit");
		closeButton.setOnAction(e -> closeApplication());

		//Refresh Button, refreshes the program
		refreshButton = new Button();
		refreshButton.setText(InputEater.Update());
		refreshButton.setOnAction(e -> addPlansList());
	}

	public void closeApplication(){					// Application closing method

		Boolean confirmExit = ExitApplicationBox.display();		// Uses external class ExitBox method display() to determine
		// if the user wants to exit the application
		if (confirmExit) {										// If true
			window.close();										// Close Stage/Application
		}
	}

}
