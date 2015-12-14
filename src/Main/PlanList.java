package Main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


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


// The PlanList class is the core class of the application. It uses JavaFX for UI creation and OpenCSV to save and read the plan information.
// It extends Application
//------------------------------------------------------------------------------------------------------------------------------------------
// 1. What the class does?
//    The class first declares all the variables and objects, then sets up a new Scene, Stage and a BorderPane layout. 
//	  Adds a menu with the logo to the top, an observable list of plans to the center and a plan editing area to the bottom. 
//    All the elements are created in 3 methods: topAreaSetup(), planTableSetup() and editPlansSetup().
// 
// 2. The Plan table
//    The planTableSetup method creates 3 columns (type,description,date) and gets all the plans by calling the getPlans method, 
//    that looks for a CSV file and reads in all its contents. It seperates the type,desc and date and crates a new plan out 
//    of them.
//
// 3. Plan editing is done through 3 buttons. 
//	  Validate Button: Calls the validateInput method to check if the user has entered the plan info and enables the Add Button.
//    Add Button: 	   Calls the addPlan method to add the plan to the plan list and append(add) the plan to the save file.
//    Delete Button:   Calls the deletePlan method to find the selected plan to be deleted and deletes it from the list and save file.
//
// 4. The Menu Bar has 3 menu items for navigating the application.
//	  Exit: Calls the closeApplication method, which in turn calls the ExitBox class display method and shows a pop-up window for confirming the exit.
//    About: Calls the AboutBox class display method which shows a pop-up window with information about the application and its author.
//    HowToPlan: Calls the HelpBox class display method which shows a pop-up window with instructions on how to use the application.

public class PlanList extends Application {

	//**** VARIABLE SETUP ****

	//Buttons
	Button closeButton;
	Button addPlanButton;
	Button deletePlanButton;
	Button validatePlanButton;

	//Validation Strings; Date and Format; Labels
	String valType;
	String valDesc;
	String valDate;
	String csv = "planSave.csv";
	String selectedDate;
	DatePicker datePicker;
	DateTimeFormatter formatDate;

	//Input Fields; Combo box
	TextField planTypeInput;
	TextField planDescInput;
	TextField planDateInput;
	ComboBox<String> planTypeCombo;


	//Stage; Layouts; Menu
	Stage window;
	MenuBar mainMenu;
	TableView<Plan> planTable;
	VBox layout;
	VBox bottomLayout;
	HBox bottomElements;

	public static void main(String[] args) {					// PLAN LIST MAIN METHOD
		launch(args);
	}

	@Override 
	public void start(Stage primaryStage) throws Exception {	// START METHOD

		window = primaryStage;						// Sets the primary stage
		window.setTitle("Muki To-Do-List");			// Sets the title of the window
		window.setOnCloseRequest(e -> { 			// Close the stage request event
			e.consume();							// Consumes the close request so we can set up a manual method for closing
			closeApplication();						// Invokes the application closing method
		});


		BorderPane layout = new BorderPane(); 		// Adds BorderPane style layout
		layout.setTop(topAreaSetup());				// Sets the menu to the top
		layout.setCenter(planTableSetup()); 		// Sets the plan table to the center
		layout.setBottom(editPlansSetup());			// Sets the plan editing to the bottom


		Scene window = new Scene(layout,800,500);	// Creates a new scene with BorderPane
		window.getStylesheets().add("Muki.css"); 	// CSS Stylesheet
		primaryStage.setScene(window); 				// Adds the Scene to the Stage

		primaryStage.setMinHeight(500);				// Sets the MAX and MIN values of the Stage
		primaryStage.setMinWidth(800);
		primaryStage.setMaxHeight(800);
		primaryStage.setMaxWidth(800);

		primaryStage.show();						// Makes the stage visible

	}

	public VBox topAreaSetup(){									// MAIN MENU SETUP METHOD

		VBox topAreaLayout = new VBox(10);


		Image logo = new Image("logo.png");									// Loads a new image
		ImageView imgPainter = new ImageView(logo);							// Creates a node to paint images
		topAreaLayout.alignmentProperty().set(Pos.TOP_CENTER);				// Sets alignment to center
		topAreaLayout.setPadding(new Insets(0,0,25,0));						// Creates some padding for beauty


		Menu fileMenu = new Menu("File");									// Creates a new menu item called File
		SeparatorMenuItem separatorPlans = new SeparatorMenuItem();			// Creates a seperator for the Plans Menu
		MenuItem exitApp = new MenuItem("Exit");							// Creates a menu item called Exit
		exitApp.setOnAction(e -> closeApplication());						// Sets the action to Exit Menu item to run the closing method
		fileMenu.getItems().addAll(separatorPlans,exitApp); 				// Adds all the menu items to the Plans Menu


		Menu helpMenu = new Menu("Help");							// Creates a new menu called Help
		MenuItem howTo = new MenuItem("How To Plan");				// Creates a new menu item called How to Plan
		howTo.setOnAction(e -> HelpBox.display());
		MenuItem aboutMuki = new MenuItem("About Muki");			// Creates a new menu item called About Muki
		aboutMuki.setOnAction(e -> AboutBox.display());				// Adds an action event to the About Muki menu item --> Display the About Box
		SeparatorMenuItem seperatorHelp = new SeparatorMenuItem(); 	// Adds a new seperator for the Help menu
		helpMenu.getItems().addAll(howTo,seperatorHelp, aboutMuki); // Add all the sub-menu items to Help Menu



		MenuBar mainMenu = new MenuBar();					// Creates a new Main Menu Bar
		mainMenu.getMenus().addAll(fileMenu,helpMenu);		// Adds the File and Help menu to the Main Menu Bar


		topAreaLayout.getChildren().addAll(mainMenu,imgPainter);
		return topAreaLayout;									// Returns the Main Menu Bar

	}

	public VBox editPlansSetup() {								// PLAN EDIT SETUP METHOD

		VBox bottomLayout = new VBox();			// Creates a new VBox for the bottom layout


		addPlanButton = new Button("Add");		// Creates a new button called Add
		addPlanButton.setDisable(true);			// Disables the button (for validation)
		addPlanButton.setOnAction(e -> {		// Adds an action event to it

			try {addPlan();					// Calls the Plan Adding method

			}
			catch (Exception e1) {
				//e1.printStackTrace();
				System.out.println("There was a problem adding the plan.");
			}
		});


		deletePlanButton = new Button("Delete");	// Creates a new button called Delete
		deletePlanButton.setOnAction(e -> {			// Adds an action event to it

			try {
				deletePlan();						// Calls the Plan Deleting method
			} catch (Exception e1) {
				//e1.printStackTrace();
				System.out.println("There was a problem deleting the plan.");
			}

		});

		validatePlanButton = new Button("Validate");	// Creates a new button called Validate
		validatePlanButton.setOnAction(e -> {			// Adds an action event to it
			validateInput();							// Calls the Input Validation Method
		});


		// PLAN TYPE INPUT
		planTypeCombo = new ComboBox<String>();			// Creates a new Combo Box for the plan type
		planTypeCombo.getItems().addAll(				// Creates and adds the choices
				"Work",
				"School",
				"Home",
				"Personal",
				"Misc"
				);
		planTypeCombo.setPromptText("Plan Type");		// Sets the prompt text
		planTypeCombo.setEditable(false);        		// Disables custom input



		// PLAN DESCRIPTION INPUT
		planDescInput = new TextField();										// Creates a new input field
		planDescInput.promptTextProperty().setValue("Plan Description"); 		// Sets the prompt text


		// DATE INPUT
		datePicker = new DatePicker();											// Creates a new Date Picker
		datePicker.promptTextProperty().setValue("Plan Date");					// Sets the prompt text
		datePicker.setOnAction(event -> {										// Adds an action event to it

			formatDate = DateTimeFormatter.ofPattern("MMM dd yyyy",Locale.ROOT);	// Date formatter with a specific pattern
			selectedDate = (datePicker.getValue()).format(formatDate);				// Gets the date selected and formats it

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
	public TableView<Plan> planTableSetup() throws IOException{ // PLAN TABLE CREATION AND SETUP

		//TYPE Column
		TableColumn<Plan, String> typeColumn = new TableColumn<>("Type"); // The title of the columnn
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


		planTable = new TableView<>(); // Creates a new TableView object
		planTable.setItems(getPlans()); // Calls getPlans() method and adds the returned plans to the table
		planTable.getColumns().addAll(typeColumn, descColumn, dateColumn); // Adds the title columns created earlier


		return planTable;
	}

	public void closeApplication(){								// APPLICATION CLOSING METHOD

		Boolean confirmExit = ExitBox.display();		// Uses external class ExitBox method display() to determine
														// if the user wants to exit the application
		if (confirmExit) {								// If true
			window.close();								// Close Stage/Application
		}
	}

	public void addPlan() throws IOException{					// PLAN ADDING METHOD


		// ADDS THE NEW PLAN TO THE PLAN TABLE
		Plan plan = new Plan();	// Creates a new plan named plan
		plan.setType(planTypeCombo.getSelectionModel().getSelectedItem()); // Gets the ComboBox selection
		plan.setDate(selectedDate);	// Get the date selected with DatePicker
		plan.setDesc(planDescInput.getText());	// Gets the plan description input
		planTable.getItems().add(plan);	// Adds the plan to the plan table

		// APPENDS NEW PLAN TO CSV FILE
		String type = planTypeCombo.getSelectionModel().getSelectedItem();
		String desc =planDescInput.getText();
		String planString = "\n"+type+";"+desc+";"+selectedDate;
		String[] planToBeSaved = planString.split(";");

		// Starts up the CSVWriter, gets the plan, adds it to the save file and closes
		CSVWriter planAdder = new CSVWriter(new FileWriter(csv,true),';',CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.NO_ESCAPE_CHARACTER);
		planAdder.writeNext(planToBeSaved);
		planAdder.close();


		// CLEARS ALL FIELDS AND RESETS ADD BUTTON
		planDescInput.clear();
		planTypeCombo.valueProperty().set("Plan Type");
		datePicker.getEditor().clear();
		addPlanButton.setDisable(true);

	}

	public void deletePlan() throws IOException{				// PLAN DELETING METHOD
		
		
		ObservableList<Plan> selectedPlan, allPlans;	//Sets up two obs. lists
		allPlans = planTable.getItems();	// All the plans so far
		selectedPlan = planTable.getSelectionModel().getSelectedItems();	// The plan that was selected in the list

		String planToDelete = selectedPlan.toString();	// The plan to be deleted is stored as a string and formatted
		String remove1= planToDelete.replace("[","");
		String remove2= remove1.replace("]","");
		planToDelete = remove2;
		
		selectedPlan.forEach(allPlans::remove);	// Goes through all the plans in the list and removes the selected plan from it


		// DELETE PLAN FROM CSV

		File file = new File(csv);	// Creates a new file object that points to the save file
		File temp = File.createTempFile("temp", ".csv", file.getParentFile());	// Creates a temp file
		String charset = "UTF-8";	// The UTF-8 coding
		
		// Creates a BufferedReader for input and a PrintWriter for output
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(temp), charset));
		
		planToDelete.toString().trim(); // Trims the plan just to be safe
		System.out.println("Looking for plan: " + planToDelete); // For debugging
		
		
		// Starts reading the save file
		for (String line; (line = reader.readLine()) != null;) {
			//
			//line.trim();

			// If a line contains the plan to be deleted, deletes the plan
			if (line.contains(planToDelete)) {

				System.out.println("Found and eradicated the plan: "+planToDelete); // For debugging
				line = line.replace(planToDelete, "");	// Eradicates the found plan 

			}
			writer.println(line); // Writes everything else to the temp file
		}

		reader.close(); // Closes the BufferedReader
		writer.close();	// Closes the PrintWriter
		file.delete();	// Deletes the old save file
		temp.renameTo(file);	// Renames the temp file to saveFile.csv
	}

	public void validateInput() {								// INPUT VALIDATION METHOD

		valDesc = planDescInput.getText();
		valType = planTypeCombo.getSelectionModel().getSelectedItem();
		//valDate = datePicker.getValue().format(formatDate).toString();

		// Enable Add Button if all requirements are met
		if (valDesc.isEmpty() == false  && valType != null) {
			addPlanButton.setDisable(false);



		}



	}

	public ObservableList<Plan> getPlans() throws IOException{	// PLAN LIST SETUP AND

		ObservableList<Plan> plans = FXCollections.observableArrayList(); // A list that allows to track changes 



		CSVReader PlanReader = new CSVReader(new FileReader(csv), ',' , '"' , 0); // A reader that uses FileReader and 3 parameters to read in a CSV file

		String[] line; // A string array called line


		while ((line = PlanReader.readNext()) != null) { // While there is a line that is not null, do the following -->

			if(line.length > 0) {	// Just a precaution


				// If there is a whitespace anywhere, continue looking
				if (line.length == 1 && line[0].isEmpty()) {
					continue;
				}

				// If there is something on a line
				if (line != null) {
					String planString = Arrays.toString(line);	// Create a string to store the value and remove the '[]' symbols
					String remove1= planString.replace("[","");
					String remove2= remove1.replace("]","");
					planString = remove2;
					String [] str = planString.split(";");		// Split the string where there is an ';'
					plans.add(new Plan (str[0],str[1],str[2])); // Add the split strings
				} 
			}      

		}
		PlanReader.close(); // Closes the CSVReader

		return plans; // Returns the found plans

	}

}
