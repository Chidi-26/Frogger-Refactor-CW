package Model;

import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTests {
    private Animal frog;

    @BeforeAll
    static void initJavaFX() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setUp(){
        frog = new Animal("/froggerUp.png");
        frog.setY(100);
        frog.setX(100);

    }
    @Test
    void testMoveUpwards(){
        frog.move(0,-frog.movement);
        assertEquals(100 - frog.movement, frog.getY(),"Animal has moved upwards");
    }
    @Test
    void testMoveDownwards(){
        frog.move(0, frog.movement);
        assertEquals(100 + frog.movement, frog.getY(), "Animal has moved downwards");
    }
    @Test
    void testMoveRight(){
        frog.move(-frog.movement, 0);
        assertEquals(100 - frog.movement, frog.getX(), "Animal has moved right");
    }
    @Test
    void testMoveLeft(){
        frog.move(frog.movement, 0);
        assertEquals(100 + frog.movement, frog.getX(),"Animal has moved to the left");
    }

    @Test
    void testGameWinCondition() {
        frog.end = 5;
        assertTrue(frog.getStop(), "Game stops when the frog reaches all end points");
    }



}
