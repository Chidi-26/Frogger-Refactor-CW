package Controller;

import Model.*;
import View.BackgroundImage;
import View.MyStage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.Objects;

/**
 * InitGame class setts up and initializes all the game elements and adds them to the background
 */
public class InitGame {

    private final MyStage background;
    Animal frog;
    private static InitGame igInstance; //singleton instance for the current InitGame class


    /**
     * Constructor to initialize the game background
     * @param background - MyStage component that sets up the background
     */
    private InitGame(MyStage background){
        this.background = background;
    }

    /**
     * Creates a singleton instance
     * @param background - MyStage component that sets up the background
     * @return instance
     */
    public static InitGame getIgInstance(MyStage background){
        if(igInstance == null){
            igInstance = new InitGame(background);
        }
        return igInstance;
    }

    /**
     * Method that initializes all the background elements
     */
    public void initElements(){
        settingBackground();
        settingObstacles();
        settingFrog();
    }

    /**
     * Method that sets up frog and adds it to the background
     */
    public void settingFrog() {
        frog = new Animal("/froggerUp.png");
        background.add(frog);
    }

    /**
     * Method that sets up obstacles and adds them to the background
     */
    private void settingObstacles() {
        //sets up truck and car obstacles
        background.add(new Obstacle("/truck1"+"Right.png", 0, 649, 1, 120, 120));
        background.add(new Obstacle("/truck1"+"Right.png", 300, 649, 1, 120, 120));
        background.add(new Obstacle("/truck1"+"Right.png", 600, 649, 1, 120, 120));
        //background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 720, 649, 1, 120, 120));
        background.add(new Obstacle("/car1Left.png", 100, 597, -1, 50, 50));
        background.add(new Obstacle("/car1Left.png", 250, 597, -1, 50, 50));
        background.add(new Obstacle("car1Left.png", 400, 597, -1, 50, 50));
        background.add(new Obstacle("/car1Left.png", 550, 597, -1, 50, 50));
        background.add(new Obstacle("/truck2Right.png", 0, 540, 1, 200, 200));
        background.add(new Obstacle("/truck2Right.png", 500, 540, 1, 200, 200));
        background.add(new Obstacle("/car1Left.png", 500, 490, -5, 50, 50));
        background.add(new digit(0, 30, 360, 25));

    }

    /**
     * Method that sets up and add background elements to the game
     */
    private void settingBackground() {

        //adds background image to stage
        BackgroundImage froggerback = new BackgroundImage("/iKogsKW.png");
        background.add(froggerback);

        //Adds logs to stage
        background.add(new Log("/log3.png", 150, 0, 166, 0.75));
        background.add(new Log("/log3.png", 150, 220, 166, 0.75));
        background.add(new Log("/log3.png", 150, 440, 166, 0.75));

        background.add(new Log("/logs.png", 300, 0, 276, -2));
        background.add(new Log("/logs.png", 300, 400, 276, -2));
        background.add(new Log("/log3.png", 150, 50, 329, 0.75));
        background.add(new Log("/log3.png", 150, 270, 329, 0.75));
        background.add(new Log("/log3.png", 150, 490, 329, 0.75));

        background.add(new Log("log3.png", 150, 570, 329, 0.75));

        background.add(new Turtle(500, 376, -1, 130, 130));
        background.add(new Turtle(300, 376, -1, 130, 130));
        background.add(new WetTurtle(700, 376, -1, 130, 130));
        background.add(new WetTurtle(600, 217, -1, 130, 130));
        background.add(new WetTurtle(400, 217, -1, 130, 130));
        background.add(new WetTurtle(200, 217, -1, 130, 130));

        //adds safe zones for the frog
        background.add(new End(13,96));
        background.add(new End(141,96));
        background.add(new End(141 + 141-13,96));
        background.add(new End(141 + 141-13+141-13+1,96));
        background.add(new End(141 + 141-13+141-13+141-13+3,96));

        background.start(); //starts animations for the background objects
    }
}
