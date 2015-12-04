package Main;

import java.util.Calendar;
import java.util.Date;

import javax.swing.text.IconView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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

		// Stage setup and closing 
		window = primaryStage;
		window.setOnCloseRequest(e -> {
			//Tells the program that we can close the program ourselves, manually
			e.consume();
			closeProgram();
		});
		window.setTitle("Muki 0.1a");



		// ****Main Menu Options setup****


		// Plans
		Menu plansMenu = new Menu("Plans");
		
		// Plans Menu Sub items
		// (New Plan; Edit Plans; Separator; Exit)
		
		MenuItem newPlan = new MenuItem("New Plan");
		//newPlan.setOnAction(e -> AuthorBox.display("Author Page", "Kristjan Saaremets C11"));

		MenuItem viewPlans = new MenuItem("Edit Plans");
		//viewPlans.setOnAction(e -> AuthorBox.display("Author Page", "Kristjan Saaremets C11"));
		
		MenuItem statistics = new MenuItem("Statistics");
		//viewPlans.setOnAction(e -> AuthorBox.display("Author Page", "Kristjan Saaremets C11"));


		SeparatorMenuItem seperatorPlans = new SeparatorMenuItem();

		MenuItem exitApp = new MenuItem("Exit");
		exitApp.setOnAction(e -> closeProgram());


		//Gets all the created sub-menu items for the Plans Menu
		plansMenu.getItems().addAll(newPlan,viewPlans,seperatorPlans,statistics,exitApp);
		


		
		
		// ****Main Menu --> Help
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

		// Add BorderPane style layout
		BorderPane layout = new BorderPane();

		//****Add to layout****
		
		// Main Menu is top
		layout.setTop(mainMenu);
		
		layout.setCenter(addPlansList());
		BorderPane.setAlignment(addPlansList(), Pos.BASELINE_CENTER);

		// Create new scene with BorderPane layout settings
		Scene window = new Scene(layout,350,600);
		
		//Add Muki.css Stylesheet to the scene
		window.getStylesheets().add("/Muki.css");
		
		// Add the scene to the Stage
		primaryStage.setScene(window);
		
		//Show the stage
		primaryStage.show();

	}	

	// Main Page Plan List Setup
	public VBox addPlansList() {
		
		// Create VBox for the list
	    VBox planList = new VBox();
	    planList.setSpacing(20);
	    planList.setAlignment(Pos.TOP_CENTER);

	    // Some test input
	    Text title = new Text("Get some food for the cat");
	    
	    Text name = new Text("Complete assignment");

	    //VBOX for displaying the My Plans image
	    VBox listImage = new VBox();
	    
	    //Load up the image
	    Image img = new Image("titlePlansList.png");
	    
	    // Paint the image with ImageView Node
	    ImageView imgView = new ImageView(img);
	    
	    //Put it in the middle
	    listImage.setAlignment(Pos.CENTER);
	    listImage.getChildren().addAll(imgView);
	    
	    
	    
	    planList.getChildren().addAll(listImage,title, name);

	    return planList;
	    }


	// Button Setup
	public void buttonSetup() {

		//Author Button, displays the author page
		authorButton = new Button();
		authorButton.setText("Display author");
		//authorButton.setOnAction(e -> AuthorBox.display("Author Page", "Kristjan Saaremets C11"));

		//Play Button
		viewPlansButton = new Button();
		viewPlansButton.setText("My Plans");
		viewPlansButton.setOnAction(e -> PlansPage.display("My Plans Page", "You have no plans yet"));

		//Info Button
		getInfoButton = new Button();
		getInfoButton.setText("Get Info");
		getInfoButton.setOnAction(e -> PlansPage.display("My Plans Page", "You have no plans yet"));

		//Exit Button, closes the program
		closeButton = new Button();
		closeButton.setText("Exit");
		closeButton.setOnAction(e -> closeProgram());
	}

	// Program closing method
	public void closeProgram(){
		// Result of ExitBox choice will determine if the program closes
		Boolean confirmer = ExitBox.display();
		if (confirmer) {
			window.close();
		}
	}

}
