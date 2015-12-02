package Main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;


public class SceneSwitcher extends Application {
	
	Stage window;
	Scene scene1, scene2;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("Works");
		window = primaryStage;
	Label label1 = new Label("Main menu");
	Label label2 = new Label("Play screen");
		
		
	//Button 1
	Button button1 = new Button("Go to play screen");
	button1.setOnAction(e -> window.setScene(scene2));
	
	//Button 2
	Button button2 = new Button("Go back to menu");
	button2.setOnAction( e -> window.setScene(scene1));
	
	
	//Layout 1
	VBox layout1 = new VBox(20);
	layout1.getChildren().addAll(label1,button1);	
	scene1 = new Scene(layout1,200,200);

	//Layout 2
	VBox layout2 = new VBox(20);
	layout2.getChildren().addAll(label2,button2);	
	scene2 = new Scene(layout2,200,200);
	
	window.setScene(scene1);
	window.setTitle("Title of scene");
	window.show();
	}
}
