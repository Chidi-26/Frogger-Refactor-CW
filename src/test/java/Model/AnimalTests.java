package Model;

import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTests {
    private Animal froggerUp;
    private Animal froggerDown;
    private Animal froggerLeft;
    private Animal froggerRight;
    private Animal animal;

    @BeforeAll
    static void initJavaFX() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setUp(){
        froggerUp = new Animal("/froggerUp.png");
        froggerDown = new Animal("/froggerDown.png");
        froggerLeft = new Animal("/froggerLeft.png");
        froggerRight = new Animal("/froggerRight.png");
        animal = new Animal("/froggerUp.png");

        froggerUp.setX(100);
        froggerUp.setY(100);
        froggerDown.setX(100);
        froggerDown.setY(100);
        froggerRight.setX(100);
        froggerRight.setY(100);
        froggerLeft.setX(100);
        froggerLeft.setY(100);
        animal.setY(100);
        animal.setX(100);


    }
    @Test
    void testMoveUpwards(){
        froggerUp.move(0,-froggerUp.movement);
        assertEquals(100 - froggerUp.movement, froggerUp.getY(),"Animal has moved upwards");
    }
    @Test
    void testMoveDownwards(){
        froggerDown.move(0, froggerDown.movement);
        assertEquals(100 + froggerDown.movement, froggerDown.getY(), "Animal has moved downwards");
    }
    @Test
    void testMoveRight(){
        froggerRight.move(-froggerRight.movement, 0);
        assertEquals(100 - froggerRight.movement, froggerRight.getX(), "Animal has moved right");
    }
    @Test
    void testMoveLeft(){
        froggerLeft.move(froggerLeft.movement, 0);
        assertEquals(100 + froggerLeft.movement, froggerLeft.getX(),"Animal has moved to the left");
    }

    @Test
    void testGameWinCondition() {
        animal.end = 5;
        assertTrue(animal.getStop(), "Game stops when the frog reaches all end points");
    }


}
