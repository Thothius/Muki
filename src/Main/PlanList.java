package Main;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
import javafx.util.StringConverter;

public class PlanList extends Application {


	//Buttons
	Button closeButton;
	Button addPlanButton;
	Button deletePlanButton;
	Button validatePlanButton;
	//Validation Strings
	String valType;
	String valDesc;
	String valDate;
	//Input Fields
	TextField planTypeInput;
	TextField planDescInput;
	TextField planDateInput;
	//Stage and layouts
	Stage window;
	VBox layout;
	MenuBar mainMenu;
	VBox bottomLayout;
	HBox bottomElements;
	//Plan Table
	TableView<Plan> planTable;
	public String selectedDate;
	ComboBox<String> planTypeCombo;
	Label infoText;
	static DateTimeFormatter formatDate;
	// Date
	static DatePicker datePicker = new DatePicker();






	// Main method
	public static void main(String[] args) {
		launch(args);
	}

	@Override 
	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;						// Sets the primary stage
		window.setTitle("Muki To-Do-List");			// Sets the title of the window
		window.setOnCloseRequest(e -> { 			// Close the stage request event
			e.consume();							// Consumes the close request 
			closeApplication();						// Invokes the application closing method
		});


		BorderPane layout = new BorderPane(); 		// Adds BorderPane style layout
		layout.setTop(mainMenuSetup());
		layout.setCenter(planTableSetup()); 
		layout.setBottom(editPlansSetup());

		//BorderPane.setAlignment(addPlansList(), Pos.CENTER); 				


		Scene window = new Scene(layout,800,500);	// Creates a new scene with BorderPane
		window.getStylesheets().add("Muki.css"); 	// CSS Stylesheet path
		primaryStage.setScene(window); 				// Adds the Scene to the Stage
		primaryStage.setMinHeight(500);
		primaryStage.setMinWidth(800);
		primaryStage.setMaxHeight(800);
		primaryStage.setMaxWidth(800);
		primaryStage.show();						// Makes the stage visible

	}

	public MenuBar mainMenuSetup(){					// Main Menu setup


		Menu plansMenu = new Menu("File");													// Creates a new Menu item called Plans
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

	public VBox editPlansSetup() {						// Button Setup


		VBox bottomLayout = new VBox();

		//infoText = new Label("Add new plans here");


		//Add btn
		addPlanButton = new Button("Add");
		addPlanButton.setDisable(true);
		addPlanButton.setOnAction(e -> {
			
				try {
					addPlan();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		});

		//Add btn
		deletePlanButton = new Button("Delete");
		deletePlanButton.setOnAction(e -> {

			deletePlan();

		});

		validatePlanButton = new Button("Validate");
		validatePlanButton.setOnAction(e -> {
			validateInput();
		});


		planTypeInput = new TextField();
		planTypeInput.promptTextProperty().setValue("Plan Type");

		// Plan Type Input as ComboBox Selection
		planTypeCombo = new ComboBox<String>();
		planTypeCombo.getItems().addAll(
				"Work",
				"School",
				"Home",
				"Personal",
				"Misc"
				);
		planTypeCombo.setPromptText("Plan Type");
		planTypeCombo.setEditable(false);        



		// Plan Description Input as TextField
		planDescInput = new TextField();
		planDescInput.promptTextProperty().setValue("Plan Description");


		datePicker.promptTextProperty().setValue("Plan Date");
		datePicker.setOnAction(event -> {
			// Formats the date
			formatDate = DateTimeFormatter.ofPattern("MMM dd, yyyy",Locale.ROOT);
			selectedDate = (datePicker.getValue()).format(formatDate);

		});


		HBox bottomElements = new HBox(10);
		bottomElements.setPadding(new Insets(30,5,30,5));
		bottomElements.getChildren().addAll(planTypeCombo,planDescInput,datePicker, validatePlanButton, addPlanButton, deletePlanButton);
		bottomLayout.getChildren().addAll(bottomElements);


		bottomLayout.setAlignment(Pos.BOTTOM_CENTER);
		bottomLayout.setPadding(new Insets(20,20,20,20));
		return bottomLayout;
	}

	@SuppressWarnings("unchecked")
	public TableView<Plan> planTableSetup() throws IOException{

		//TYPE Column
		TableColumn<Plan, String> typeColumn = new TableColumn<>("Type");
		typeColumn.setMinWidth(75);
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

		//DESCRIPTION Column
		TableColumn<Plan, String> descColumn = new TableColumn<>("Description");
		descColumn.setMinWidth(580);
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

	public void addPlan() throws IOException{							// Method for adding plans


		// ADDS THE NEW PLAN TO THE PLAN TABLE
		Plan plan = new Plan();
		plan.setType(planTypeCombo.getSelectionModel().getSelectedItem());
		plan.setDate(selectedDate);
		plan.setDesc(planDescInput.getText());
		planTable.getItems().add(plan);
		
		
		
		// SAVES PLAN TO CSV FILE
		String csv = "C:/Users/Kristjan/workspace/Muki/src/planSave.csv";
		String type = planTypeCombo.getSelectionModel().getSelectedItem();
		String desc =planDescInput.getText();
		String planString = type+";"+desc+";"+selectedDate;
		String[] planToBeSaved = planString.split(";");
		CSVWriter writer = new CSVWriter(new FileWriter(csv,true),';',CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.NO_ESCAPE_CHARACTER);
		writer.writeNext(planToBeSaved);
		writer.close();
		
		
		
	
		// CLEARS ALL FIELDS AND RESETS ADD BUTTON
		planDescInput.clear();
		planTypeCombo.valueProperty().set("Plan Type");
		datePicker.getEditor().clear();
		addPlanButton.setDisable(true);
		
		
	
		
		
		
		
		
		

	}

	public void deletePlan(){						// Method for deleting plans

		ObservableList<Plan> selectedPlans, allPlans;
		allPlans = planTable.getItems();
		selectedPlans = planTable.getSelectionModel().getSelectedItems();
		selectedPlans.forEach(allPlans::remove);
	}

	public void validateInput() {

		valDesc = planDescInput.getText();
		valType = planTypeCombo.getSelectionModel().getSelectedItem();
		//valDate = datePicker.getValue().format(formatDate).toString();

		// Enable Add Button if all requirements are met
		if (valDesc.isEmpty() == false  && valType != null) {
			addPlanButton.setDisable(false);



		}



	}

	public ObservableList<Plan> getPlans() throws IOException{			// Observable List of plans, plan generation

		ObservableList<Plan> plans = FXCollections.observableArrayList();

		@SuppressWarnings("resource")


		CSVReader reader = new CSVReader(new FileReader("C:/Users/Kristjan/workspace/Muki/src/planSave.csv"), ',' , '"' , 2);

		String[] line;
		System.out.println("Reading plan list...");
		while ((line = reader.readNext()) != null) {
			if (line != null) {
				String planString = Arrays.toString(line);
				String remove1= planString.replace("[", "");
				String remove2= remove1.replace("]","");
				planString = remove2;
				String [] str = planString.split(";");

				System.out.println(planString);


				plans.add(new Plan (str[0],str[1],str[2]));

			}
		}

		return plans;

	}






}
