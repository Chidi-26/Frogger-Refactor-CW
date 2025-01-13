import Controller.GameManager;
import Controller.InitGame;
import View.MyStage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * {@code Main} class launches the frogger game into an app
 * Sets up all the background elements and the game stage, and the launches the app
 */
public class Main extends Application {
	MyStage background;
	InitGame initialiseGame;
	GameManager gameManager;

	/**
	 * {@code main} method essentially launches the app
	 * @param args - passes through command line arguments into the application
	 */
	public static void main(String[] args) {
		launch(args); //launches app
	}

	/**
	 * {@code start} method starts the game by initializing it
	 * @param primaryStage - JavaFX app primary stage - creates a window
	 * @throws Exception - is thrown when there is an error in the initializing process
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		background = new MyStage(); //initialising stage
		Scene scene  = new Scene(background,600,800); //creating stage dimension

		//using singleton instances to initialise everything pertaining to the game
		initialiseGame =  InitGame.getIgInstance(background);
		gameManager = GameManager.getGmInstance(background, initialiseGame);

		//initializes the background
		initialiseGame.initElements();

		//sets scene and primary stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("You Have 10 Lives!");
		primaryStage.show();
		gameManager.start(); //starts the application
	}


}
