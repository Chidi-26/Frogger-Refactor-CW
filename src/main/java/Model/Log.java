package Model;

import javafx.scene.image.Image;

/**
 * {@code Log} class extends {@code Actor}
 * Essentially sets the position, speed and image of the log object which helps the frog to cross the water without dying
 */

public class Log extends Actor {

	private double speed;

	/**
	 * Defines the logic and how the log object would behave
	 * @param now - represents the current time
	 */
	@Override
	public void act(long now) {
		move(speed , 0);
		if (getX()>600 && speed>0)
			setX(-180);
		if (getX()<-300 && speed<0)
			setX(700);
	}

	/**
	 * Constructor that sets up the log's image, size, speed and location
	 * @param imageLink - sets the image and how the log looks like
	 * @param size - sets the size of the log
	 * @param xpos - sets the x coordinates of the log
	 * @param ypos - sets the y coordinates of the log
	 * @param s - represents the speed at which the log is moving across the water
	 */
	public Log(String imageLink, int size, int xpos, int ypos, double s) {
		setImage(new Image(imageLink, size,size, true, true));
		setX(xpos);
		setY(ypos);
		speed = s;
		
	}

	/**
	 * Checks if the log is moving in the left direction
	 * @return true if the speed is less than 0, if it is 0 or more then it returns false
	 */
	public boolean getLeft() {
		return speed < 0;
	}
}
