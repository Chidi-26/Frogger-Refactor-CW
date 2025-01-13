package Model;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * {@code Animal} class extends {@code Actor}
 * Initializes the frogs images and its default starting position and sets up its corresponding event handlers
 */

public class Animal extends Actor {
	Image imgW1, imgA1, imgS1, imgD1, imgW2, imgA2, imgS2, imgD2;

	int lives = 10;
	private boolean frogDeath = false;
	int points = 0;
	int end = 0;
	int imgSize = 40;
	int carD = 0;
	double w = 800;

	private boolean second = false;
	boolean noMove = false;
	double movement = 26.6666666;

	boolean carDeath = false;
	boolean waterDeath = false;
	boolean stop = false;
	boolean changeScore = false;

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
		setX(300);
		setY(679.8+movement);

		initFroggerImages();

		settingUpKeyHandlers();
	}

	/**
	 * {@code initFroggerImages} method sets up and initializes the different frog images
	 */
	public void initFroggerImages(){
		imgW1 = new Image("/froggerUp.png", imgSize, imgSize, true, true);
		imgA1 = new Image("/froggerLeft.png", imgSize, imgSize, true, true);
		imgS1 = new Image("/froggerDown.png", imgSize, imgSize, true, true);
		imgD1 = new Image("/froggerRight.png", imgSize, imgSize, true, true);
		imgW2 = new Image("/froggerUpJump.png", imgSize, imgSize, true, true);
		imgA2 = new Image("/froggerLeftJump.png", imgSize, imgSize, true, true);
		imgS2 = new Image("/froggerDownJump.png", imgSize, imgSize, true, true);
		imgD2 = new Image("/froggerRightJump.png", imgSize, imgSize, true, true);
	}

	/**
	 * {@code settingUpKeyHandlers} method sets up key event handlers for frog movement.
	 */
	public void settingUpKeyHandlers(){
		setOnKeyPressed(this:: pressedKey);
		setOnKeyReleased(this :: releasedKey);
	}

	/**
	 * {@code pressedKey} handles key pressed logic
	 * @param event triggered by a key pressed
	 */
	private void pressedKey(KeyEvent event){
		var code = event.getCode();
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
	 * {@code releasedKey} method handles key released logic
	 * @param event - triggered by user
	 */
	private void releasedKey(KeyEvent event){
		var code = event.getCode();

        if (event.getCode() == KeyCode.W && getY() < w) {
				changeScore = true;
				w = getY();
				points += 10;
			}
				doMove(code, imgW1, imgA1, imgS1, imgD1);
				second = false;


	}

	/**
	 * {@code doMove} method setts up frog image based on direction of movement and executes movement
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
	 * {@code settingImageAndMove} method sets the image and moves the frog in the desired direction
	 * @param anyImage - image, sets the intended image
	 * @param dx - moves in the x direction
	 * @param dy - moves in the y direction
	 */
	public void settingImageAndMove(Image anyImage, double dx, double dy){
		setImage(anyImage);
		move(dx, dy);
	}


	/**
	 * {@code act} method is inherited from the {@code Actor} class
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
	 * {@code collisionsHandled} method handles collisions that frog makes with objects
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
	 * {@code waterDeathHandled} method handles when frog dies drowns in the water
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
	 * {@code resetFrogAfterDeath} method resets frog after it dies from either being hit by a car or drowning in the weater
	 */
	public void resetFrogAfterDeath() {
		liveLost();
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
	 * {@code carDeathHandled} method handles when frog dies from being hit by a car
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
	 * {@code checkBoundaries} method checks whether frog is in the boundaries and resets the frog if it attempts to go outside it in the y direction
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
	 * {@code resetFrog} method resets frog to its initial starting position
	 */
	public void resetFrog(){
		setX(300);
		setY(679.8+movement);
	}

	/**
	 * {@code getStop} is a getter method that shows that the game stops when all 5 safe zones have been attained
	 * @return end and sets it equal to 5
	 */
	public boolean getStop() {
		return end == 5;
	}

	/**
	 * {@code getPoints} method is a getter function to return points
	 * @return points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * {@code changeScore} method checks whether the score has changed
	 * If it has changed it then resets the variable flag to false, saving the changes
	 * @return true if there has been a change in score and false if otherwise
	 */
	public boolean changeScore() {
		//if score has changed
		if (changeScore) {
			changeScore = false;
			return true; //returns true if score has changed
		}
		return false; //returns false if no change has been made

	}

	/**
	 * {@code getLives} is a getter method to return number of lives
	 * @return lives
	 */
	public int getLives(){
		return lives;
	}

	/**
	 * {@code setLives} sets the amount of lives the frog has
	 * @param lives - sets the lives variable to the imputed lives from the argument in the method
	 */
	public void setLives(int lives){
		this.lives = lives;
	}

	/**
	 * {@code isFrogDead} method checks whether the frog is dead or not
	 * @return returns false if frog isn't dead otherwise it returns true
	 */
	public boolean isFrogDead(){
		return frogDeath;
	}

	/**
	 * {@code liveLost} method handles when the frog dies and loses a life
	 */
	public void liveLost(){
		if(lives > 0){
			lives--;
		}
		frogDeath = false;
	}

	/**
	 * {@code frogLivesFinished} method ensures that the game ends once the frog lives hits 0
	 * @return lives and sets it to 0
	 */
	public boolean frogLivesFinished(){
		return lives == 0;
	}

}
