package Model;

import java.util.ArrayList;

import javafx.event.EventHandler;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class Animal extends Actor {
	Image imgW1, imgA1, imgS1, imgD1, imgW2, imgA2, imgS2, imgD2; //variables for different movement states

	int points = 0; //variable that tracks points that the player gets
	int end = 0; // variable that tracks the number of end zones
	int imgSize = 40; //sets the image size to 40
	int carD = 0; //variable to manage car death
	double w = 800;

	private boolean second = false; //flag that tracks the jumping animation
	boolean noMove = false; // flag that prevents any movement
	double movement = 26.6666666; //
	double movementX = 21.333332;
	boolean carDeath = false; //flag to see if frog died via car
	boolean waterDeath = false; //flag to see if flog died via water
	boolean stop = false; //flag to see if the game should stop
	boolean changeScore = false; //flag to see if the game score has changed

	ArrayList<End> inter = new ArrayList<End>();

	/**
	 * Constructor for the Animal class.
	 * Initializes the frog's image, position, and movement states.
	 * Sets up event handlers to ensure movement.
	 *
	 * @param imageLink The initial image for the frog.
	 */
	public Animal(String imageLink) {
		setImage(new Image(imageLink, imgSize, imgSize, true, true));
		setX(300); // setting x to 300 - starting position
		setY(679.8+movement); // setting y - starting position

		//initialising image variables
		imgW1 = new Image("/froggerUp.png", imgSize, imgSize, true, true);
		imgA1 = new Image("/froggerLeft.png", imgSize, imgSize, true, true);
		imgS1 = new Image("/froggerDown.png", imgSize, imgSize, true, true);
		imgD1 = new Image("/froggerRight.png", imgSize, imgSize, true, true);
		imgW2 = new Image("/froggerUpJump.png", imgSize, imgSize, true, true);
		imgA2 = new Image("/froggerLeftJump.png", imgSize, imgSize, true, true);
		imgS2 = new Image("/froggerDownJump.png", imgSize, imgSize, true, true);
		imgD2 = new Image("/froggerRightJump.png", imgSize, imgSize, true, true);

		//setting up event handlers for key presses and releases
		settingUpKeyHandlers();
	}

	/**
	 * Sets up key event handlers for frog movement.
	 */
	public void settingUpKeyHandlers(){
		setOnKeyPressed(this:: pressedKey);
		setOnKeyReleased(this :: releasedKey);
	}

	/**
	 * Handles key pressed logic
	 * @param event triggered by a key pressed
	 */
	private void pressedKey(KeyEvent event){
		var code = event.getCode();
		if(noMove){

		}
		if (second) {
				doMove(code, imgW1, imgA1, imgS1, imgD1);
					changeScore = false;
					second = false;
		} else {
			doMove(code, imgW2, imgA2, imgS2, imgD2);
			second = true;
		}
	}

	/**
	 * Handles key released logic
	 * @param event - triggered by user
	 */
	private void releasedKey(KeyEvent event){
		var code = event.getCode();
		if (noMove) {}

			if (event.getCode() == KeyCode.W && getY() < w) {
				changeScore = true;
				w = getY();
				points += 10;
			}
				doMove(code, imgW1, imgA1, imgS1, imgD1);
				second = false;


	}

	/**
	 * Setts up frog image based on direction of movement and executes movement
	 * @param code the KeyCode key input for direction
	 * @param iUp - image for upward movement
	 * @param iLeft - image for movement to the left
	 * @param iDown - image for moving downward
	 * @param iRight - image for moving to the right
	 */
	public void doMove(KeyCode code, Image iUp, Image iLeft, Image iDown, Image iRight ){
		switch (code){
			case W -> settingImageAndMove(iUp, 0, -movement);
			case A -> settingImageAndMove(iLeft, -movement, 0);
			case S -> settingImageAndMove(iDown, 0, movement);
			case D -> settingImageAndMove(iRight, movement, 0);
		}
	}

	/**
	 * Sets the image and moves the frog in the desired direction
	 * @param anyImage image, sets the image
	 * @param dx - moves in the x direction
	 * @param dy - moves in the y direction
	 */
	public void settingImageAndMove(Image anyImage, double dx, double dy){
		setImage(anyImage);
		move(dx, dy);
	}


	/**
	 * Handles boundaries, collisions and water deaths and is called every game tick
	 * @param now - current time stamp
	 */
	@Override
	public void act(long now) {
		checkBoundaries();
		carDeathHandled(now);

		waterDeathHandled(now);

		collisionHandled();
	}

	/**
	 * Handles collisions that frog makes with objects
	 */
	public void collisionHandled(){
		if (!getIntersectingObjects(Obstacle.class).isEmpty()) {
			carDeath = true;
		}
		if (getX() == 240 && getY() == 82) {
			stop = true;
		}
		if (!getIntersectingObjects(Log.class).isEmpty() && !noMove) {
			if(getIntersectingObjects(Log.class).getFirst().getLeft())
				move(-2,0);
			else
				move (.75,0);
		}
		else if (!getIntersectingObjects(Turtle.class).isEmpty() && !noMove) {
			move(-1,0);
		}
		else if (!getIntersectingObjects(WetTurtle.class).isEmpty()) {
			if (getIntersectingObjects(WetTurtle.class).getFirst().isSunk()) {
				waterDeath = true;
			} else {
				move(-1,0);
			}
		}
		else if (!getIntersectingObjects(End.class).isEmpty()) {
			inter = (ArrayList<End>) getIntersectingObjects(End.class);
			if (getIntersectingObjects(End.class).getFirst().isActivated()) {
				end--;
				points-=50;
			}
			points+=50;
			changeScore = true;
			w=800;
			getIntersectingObjects(End.class).getFirst().setEnd();
			end++;
			resetFrog();
		}
		else if (getY()<413){
			waterDeath = true;
			//setX(300);
			//setY(679.8+movement);
		}
	}

	/**
	 * Handles when frog dies drowns in the water
	 * @param now - represents the current time stamp
	 */
	private void waterDeathHandled(long now){
		if (waterDeath) {
			noMove = true;
			if ((now)% 11 ==0) {
				carD++;
			}
			switch (carD){
				case 1 -> setImage(new Image("/waterdeath1.png", imgSize,imgSize , true, true));
				case 2 -> setImage(new Image("/waterdeath2.png", imgSize,imgSize , true, true));
				case 3 -> setImage(new Image("/waterdeath3.png", imgSize,imgSize , true, true));
				case 4 -> setImage(new Image("/waterdeath4.png", imgSize,imgSize , true, true));
                case 5 -> resetFrogAfterDeath();

            }

		}
	}

	/**
	 * Resets frog after it dies from either being hit by a car or drowning in the weater
	 */
	public void resetFrogAfterDeath() {
		resetFrog();
		waterDeath = false;
		carDeath = false;
		carD = 0;
		setImage(new Image("/froggerUp.png", imgSize, imgSize, true, true));
		noMove = false;
		if (points > 50) {
			points -= 50;
			changeScore = true;
		}
	}

	/**
	 * Handles when frog dies from being hit by a car
	 * @param now - current time stamp
	 */
	private void carDeathHandled(long now){
		if (carDeath) {
			noMove = true;
			if ((now)% 11 ==0) {
				carD++;
			}
			switch (carD){
				case 1 -> setImage(new Image("/cardeath1.png", imgSize, imgSize, true, true));
				case 2 -> setImage(new Image("/cardeath2.png", imgSize, imgSize, true, true));
				case 3 -> setImage(new Image("/cardeath3.png", imgSize, imgSize, true, true));
				case 4 -> resetFrogAfterDeath();
			}

		}
	}

	/**
	 * Checks whether frog is in the boundaries and resets the frog if it attempts to go outside it in the y direction
	 * In the x direction, the frog is unable to leave the boundaries
	 */
	private void checkBoundaries(){
		if (getY()<0 || getY()>734) {
			resetFrog();
		}
		if (getX()<0) {
			move(movement*2, 0);
		}
		if (getX()>600) {
			move(-movement*2, 0);
		}
	}

	/**
	 * Resets frog to its initial starting position
	 */
	public void resetFrog(){
		setX(300);
		setY(679.8+movement);
	}

	/**
	 * Game stops when all 5 safe zones have been attained
	 * @return end and sets it equal to 5
	 */
	public boolean getStop() {
		return end == 5;
	}

	/**
	 * Getter function to return points
	 * @return points
	 */
	public int getPoints() {
		return points;
	}

	public boolean changeScore() {
		//if score has changed
		if (changeScore) {
			changeScore = false;
			return true; //returns true if score has changed
		}
		return false; //returns false if no change has been made

	}

}
