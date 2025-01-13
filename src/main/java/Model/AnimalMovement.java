package Model;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class FrogMovement {

    Animal frog;
    double movement = 26.6666666;

    public FrogMovement(Animal frog){
        this.frog = frog;
    }

    /**
     * {@code HandlesKeyReleased} method handles when certain keys are released
     * @param event - triggered by player
     */
    public void HandlesKeyReleased(KeyEvent event){
        var code = event.getCode();
        switch (code){
            case W -> settingImageAndMove(frog.getUpPic(), 0, -movement);
            case A -> settingImageAndMove(frog.getLeftPic(), -movement, 0);
            case S -> settingImageAndMove(frog.getDownPic(), 0, movement);
            case D -> settingImageAndMove(frog.getRightPic(), movement, 0);
        }
    }

    public void HandlesKeyPressed(KeyEvent event){
        var code = event.getCode();
        switch (code){
            case W -> settingImageAndMove(frog.getUpJumpPic(), 0, -movement);
            case A -> settingImageAndMove(frog.getLeftJumpPic(), -movement, 0);
            case S -> settingImageAndMove(frog.getDownJumpPic(), 0, movement);
            case D -> settingImageAndMove(frog.getRightPic(), movement, 0);
        }
    }
    /**
     * {@code checkBoundaries} method checks whether frog is in the boundaries and resets the frog if it attempts to go outside it in the y direction
     * In the x direction, the frog is unable to leave the boundaries
     */
    private void checkBoundaries(){
        if (frog.getY()<0 || frog.getY()>734) {
            frog.resetFrog();
        }
        if (frog.getX()<0) {
            frog.move(movement*2, 0);
        }
        if (frog.getX()>600) {
            frog.move(-movement*2, 0);
        }
    }

    /**
     * {@code settingImageAndMove} method sets the image and moves the frog in the desired direction
     * @param anyImage - image, sets the intended image
     * @param dx - moves in the x direction
     * @param dy - moves in the y direction
     */
    public void settingImageAndMove(Image anyImage, double dx, double dy){
        frog.setImage(anyImage);
        frog.move(dx, dy);
    }
}
