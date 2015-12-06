package Main;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.*;


public class NewPlanBox {
	
	
	String result;
	
	public static void display(){
		
		//Label title = new Label("New Plan");
		
		// 00 10
		// 01 11
		// 02 12
		
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("New Plan");
		window.setMinWidth(600);
		window.setMinHeight(400);
		
		
		GridPane layout = new GridPane();
		layout.setPadding(new Insets(0,50,50,50));
		layout.setVgap(20);
		layout.setHgap(20);
		layout.setAlignment(Pos.CENTER);

		VBox imgLayout = new VBox();
		imgLayout.setAlignment(Pos.TOP_CENTER);
		Image img = new Image("titleNewPlan.png");	// Loads a new image
		ImageView imgPainter = new ImageView(img);		// Creates a node to paint images		
		imgLayout.getChildren().add(imgPainter);
		
		
		
		Label planName = new Label("Plan Type ");
		layout.add(planName, 0, 0);
		
		
		Label planDate = new Label("Plan Date ");
		layout.add(planDate, 0, 1);

		Label planDesc = new Label("Plan Description ");
		layout.add(planDesc, 0, 2);
		
		
		TextField typeInput = new TextField();
		typeInput.setPromptText("Work, home, hobby etc");
		layout.add(typeInput, 1, 0);
		
		
		TextField dateInput = new TextField();
		dateInput.setPromptText("Enter the end date");
		layout.add(dateInput, 1, 1);
		
		TextField descInput = new TextField();
		descInput.setPromptText("Enter plan description");
		layout.add(descInput, 1, 2);
		
		//layout.getColumnConstraints().add(new ColumnConstraints(100));
		
		//Label authorLabel = new Label();
		//authorLabel.setText(name);
		
		// Back Button
		Button closeButton = new Button("Cancel Plan");
		closeButton.setOnAction( e-> window.close());
		layout.add(closeButton, 0, 4);
		
		// Submit Button
		Button submitButton = new Button("Submit Plan");
		submitButton.setOnAction( e-> {
			
			String type = typeInput.getText();
			String date = dateInput.getText();
			String desc = descInput.getText();
			String result = type + " | " + desc + " | " + date;
			InputEater.GetPlan(result);
			InputEater.Update();
			window.close();
			
		});
		layout.add(submitButton, 0, 3);
		
		VBox master = new VBox();
		master.getChildren().addAll(imgLayout, layout);
		
		
		
		
		//layout.getChildren().addAll(closeButton);
		

	//	VBox layout = new VBox(20);
	//	layout.getChildren().addAll(authorLabel, closeButton);
	//	layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(master);
		scene.getStylesheets().add("/Muki.css");

		window.setScene(scene);
		window.showAndWait();
	}
	
	
}
