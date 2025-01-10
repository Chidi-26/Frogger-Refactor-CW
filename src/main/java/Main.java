import Controller.GameManager;
import Controller.InitGame;
import Model.*;
import View.MyStage;
import Model.Obstacle;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Main extends Application {
	MyStage background;
	InitGame initialiseGame;
	GameManager gameManager;

	public static void main(String[] args) {
		launch(args); //launches app
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		background = new MyStage(); //initialising stage
		Scene scene  = new Scene(background,600,800); //creating stage dimension

		initialiseGame = new InitGame(background);
		gameManager = new GameManager(background, initialiseGame);

		initialiseGame.initElements();

		//sets scene and primary stage
		primaryStage.setScene(scene);
		primaryStage.show();
		gameManager.start(); //starts the application
	}


}
