package Controller;

import Model.digit;
import View.MyStage;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Alert;


/**
 * {@code GameManager} class follows the singleton design pattern and essentially manages the in game logic
 */
public class GameManager {
    private final MyStage background;
    private AnimationTimer timer;
    private final InitGame initGame;
    private static GameManager gmInstance;

    /**
     *  Private constructor
     * @param background - plays the background music
     * @param initGame - initializes every element of the game
     */

    private GameManager(MyStage background, InitGame initGame) {
        this.background = background;
        this.initGame = initGame;

    }

    /**
     * {@code GameManager} method gets the singleton instance from the {@code GameManager} class while also creating one if it doesn't already exsist
     * @param background - plays the background music
     * @param initGame - initializes every element of the game
     * @return the singleton instance of the {@code GameManager} class
     */
    public static GameManager getGmInstance(MyStage background, InitGame initGame){
        if(gmInstance == null){
            gmInstance = new GameManager(background, initGame);
        }
        return gmInstance;
    }

    /**
     * {@code createTimer} method that creates a timer and checks if the game has ended
     */
    public void createTimer() {
        var frog = initGame.frog;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (frog.changeScore()) {
                    setNumber(frog.getPoints());
                }
                if (frog.getStop()) {
                    System.out.print("STOPP:");
                    background.stopMusic();
                    GameManager.this.stop();
                    background.stop();
                   displayWinningMessage();
                }
            }
        };
    }

    /**
     * {@code displayWinningMessage} method displays a message once the player has won the game
     */
    private void displayWinningMessage(){
        var frog = initGame.frog;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("You Have Won The Game!");
        alert.setHeaderText("Your High Score: "+frog.getPoints()+"!");
        alert.setContentText("Highest Possible Score: 800");
        alert.show();
    }

    /**
     * {@code start} method begins to play the background music and starts the timer
     */
    public void start() {
        background.playMusic();
        createTimer();
        timer.start();
    }

    /**
     * {@code stop} method stops the timer
     */
    public void stop() {
        timer.stop();
    }

    /**
     * {@code setNumber} method updates the score attained from the player
     * @param n - the current score attained by the player
     */
    public void setNumber(int n) {
        int shift = 0;
        while (n < 0) {
            int d = n / 10;
            int k = n - d * 10;
            n = d;
            background.add(new digit(k, 30, 360 - shift, 25));
            shift+=30;
        }
    }

}
