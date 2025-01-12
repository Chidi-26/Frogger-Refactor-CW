package Controller;

import Model.Animal;
import Model.digit;
import View.MyStage;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Alert;

import java.util.Objects;

public class GameManager {
    private final MyStage background;
    private AnimationTimer timer;
    private final InitGame initGame;

    public GameManager(MyStage background, InitGame initGame) {
        this.background = background;
        this.initGame = initGame;

    }
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

    private void displayWinningMessage(){
        var frog = initGame.frog;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("You Have Won The Game!");
        alert.setHeaderText("Your High Score: "+frog.getPoints()+"!");
        alert.setContentText("Highest Possible Score: 800");
        alert.show();
    }
    //plays the music and starts timer
    public void start() {
        background.playMusic();
        createTimer();
        timer.start();
    }

    //stops timer
    public void stop() {
        timer.stop();
    }

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
