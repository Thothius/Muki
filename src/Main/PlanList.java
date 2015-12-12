package Main;





import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlanList extends Application {

	
	//Buttons
	Button closeButton;
	Button addPlanButton;
	Button deletePlanButton;
	
	//Input Fields
	TextField planTypeInput;
	TextField planDescInput;
	TextField planDateInput;

	
	Stage window;
	VBox layout;
	MenuBar mainMenu;
	TableView<Plan> planTable;
	HBox bottomLayout;


	// Main method
	public static void main(String[] args) {
		launch(args);
	}


	@Override 
	public void start(Stage primaryStage) throws Exception {


		window = primaryStage;						// Sets the primary stage
		window.setOnCloseRequest(e -> { 			// Close the stage request event
			e.consume();							// Consumes the close request 
			closeApplication();						// Invokes the application closing method
		});

		window.setTitle("Muki");					// Sets the title of the window

		BorderPane layout = new BorderPane();		// Adds BorderPane style layout
		//layout.setPadding(new Insets(0,10,0,10));
		layout.setTop(mainMenuSetup());								// Sets the Main Menu Bar to top
		layout.setCenter(planTableSetup()); 								// Sets the Plans List to the center
		layout.setBottom(editPlansSetup()); 								// Sets the Refresh button to the bottom
		BorderPane.setAlignment(addPlansList(), Pos.CENTER); 	// Positions the Plans List in the center


		Scene window = new Scene(layout,720,500);	// Creates a new scene with BorderPane
		window.getStylesheets().add("/Muki.css"); 	// CSS Stylesheet path
		primaryStage.setScene(window); 				// Adds the Scene to the Stage
		primaryStage.setMinHeight(500);
		primaryStage.setMinWidth(720);
		primaryStage.setMaxHeight(800);
		primaryStage.setMaxWidth(720);
		primaryStage.show();						// Makes the stage visible

	}

	public MenuBar mainMenuSetup(){					// Main Menu setup


		Menu plansMenu = new Menu("File");													// Creates a new Menu item called Plans
		//MenuItem newPlan = new MenuItem("New Plan");										// Creates a menu item called New Plan
		//newPlan.setOnAction(e -> NewPlanBox.display());										// Sets the action to New Plan to display the Plan Page
		//MenuItem viewPlans = new MenuItem("Edit Plans");									// Creates a menu item called Edit PLans
		MenuItem statistics = new MenuItem("Statistics");									// Creates a menu item called Statistics
		SeparatorMenuItem separatorPlans = new SeparatorMenuItem();							// Creates a seperator for the Plans Menu
		MenuItem exitApp = new MenuItem("Exit");											// Creates a menu item called Exit
		exitApp.setOnAction(e -> closeApplication());										// Sets the action to Exit to run the closing method
		plansMenu.getItems().addAll(statistics,separatorPlans,exitApp); 	// Adds all the creates menu items to the Plans Menu




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
		planList.setSpacing(20);				// Sets layout item spacing
		planList.setAlignment(Pos.TOP_CENTER);	// Positions the layout to center top

		VBox listImage = new VBox();					// A VBox node for Plans List image
		Image img = new Image("titlePlansList.png");	// Loads a new image
		ImageView imgPainter = new ImageView(img);		// Creates a node to paint images		
		listImage.setAlignment(Pos.CENTER);				// Alignes the image to the center
		listImage.getChildren().addAll(imgPainter);		// Adds the image to the VBox node
		planList.getChildren().addAll(listImage);	// Adds Image node and text node to the plansList node







		return planList;	// Return the planList node					
	}

	public HBox editPlansSetup() {						// Button Setup


		//Add btn
		addPlanButton = new Button("Add");
		addPlanButton.setOnAction(e -> {
			addPlan();
		});

		//Add btn
		deletePlanButton = new Button("Delete");
		deletePlanButton.setOnAction(e -> {
			
			deletePlan();
			
		});

		planTypeInput = new TextField();
		planTypeInput.promptTextProperty().setValue("Plan Type");

		planDescInput = new TextField();
		planDescInput.promptTextProperty().setValue("Plan Description");

		planDateInput = new TextField();
		planDateInput.promptTextProperty().setValue("Plan Date");

		HBox bottomLayout = new HBox(10);
		bottomLayout.setPadding(new Insets(30,10,30,10));
		bottomLayout.getChildren().addAll(planTypeInput,planDescInput,planDateInput, addPlanButton, deletePlanButton);
		return bottomLayout;
	}

	@SuppressWarnings("unchecked")
	public TableView<Plan> planTableSetup(){
		
		//TYPE Column
				TableColumn<Plan, String> typeColumn = new TableColumn<>("Type");
				typeColumn.setMinWidth(75);
				typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

				//DESCRIPTION Column
				TableColumn<Plan, String> descColumn = new TableColumn<>("Description");
				descColumn.setMinWidth(500);
				descColumn.setCellValueFactory(new PropertyValueFactory<>("desc"));

				//DATE Column
				TableColumn<Plan, String> dateColumn = new TableColumn<>("Date");
				dateColumn.setMinWidth(122);
				dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));


				planTable = new TableView<>();
				planTable.setItems(getPlans());
				planTable.getColumns().addAll(typeColumn, descColumn, dateColumn);
				
				return planTable;
	}
	
	public void closeApplication(){					// Application closing method

		Boolean confirmExit = ExitBox.display();		// Uses external class ExitBox method display() to determine
		// if the user wants to exit the application
		if (confirmExit) {										// If true
			window.close();										// Close Stage/Application
		}
	}

	public void addPlan(){

	//Need to validate input!!!!	
		
	
	Plan plan = new Plan();
	plan.setType(planTypeInput.getText());
	plan.setDesc(planDescInput.getText());
	plan.setDate(planDateInput.getText());
	planTable.getItems().add(plan);
	planTypeInput.clear();
	planDescInput.clear();
	planDateInput.clear();
	}
	
	public void deletePlan(){
		
		ObservableList<Plan> selectedPlans, allPlans;
		allPlans = planTable.getItems();
		selectedPlans = planTable.getSelectionModel().getSelectedItems();
		selectedPlans.forEach(allPlans::remove);
	}
	
	public ObservableList<Plan> getPlans(){

		ObservableList<Plan> plans = FXCollections.observableArrayList();

		plans.add(new Plan ("Home", "Get food for the cat", "December 13"));
		plans.add(new Plan ("School", "Complete assignment", "December 14"));
		return plans;



	}
}
