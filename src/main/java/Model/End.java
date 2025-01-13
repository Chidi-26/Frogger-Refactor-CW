package Model;

import javafx.scene.image.Image;

/**
 * {@code End} class extends {@code Actor} and represents each individual end safe zone area within the app
 */

public class End extends Actor{
	boolean activated = false;

	/**
	 * {@code act} method is inherited from the {@code Actor} class
	 * @param now - represents the current time
	 */
	@Override
	public void act(long now) {
		// TODO Auto-generated method st
	}

	/**
	 * Constructor - imputes the end safe zone at a specific location and sets up and shows the end image
	 * @param x - sets X coordination
	 * @param y - sets Y coordination
	 */
	public End(int x, int y) {
		setX(x);
		setY(y);
		setImage(new Image("/End.png", 60, 60, true, true));
	}

	/**
	 * Once the frog reaches the safe zone display the end image
	 */
	public void setEnd() {
		setImage(new Image("/FrogEnd.png", 70, 70, true, true));
		activated = true;
	}

	/**
	 * Checks if the end zone flag is activated
	 * @return false if not activated and true if it has been activated
	 */
	public boolean isActivated() {
		return activated;
	}
	

}
