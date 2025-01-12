package Model;

import javafx.scene.image.Image;

/**
 * {@code Obstacle} class extends {@code Actor}
 * Sets up the image, position, speed and size of the obstacle and also how the obstacle behaves
 * The frog is meant to avoid colliding with these obstacles
 */

public class Obstacle extends Actor {
	private int speed;

	/**
	 * {@code act} method is inherited by the {@code Actor} class
	 * Defines the behaviour of the obstacle
	 * @param now - represents the current time
	 */
	@Override
	public void act(long now) {
		move(speed , 0);
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -50 && speed<0)
			setX(600);
	}

	/**
	 * Constructor that imputes the object image, position, speed and size
	 * @param imageLink - image of the object
	 * @param xpos - sets x coordinate of the object
	 * @param ypos - sets y coordinate of the object
	 * @param s - sets the speed of the object
	 * @param w - sets the width of the object
	 * @param h - sets the height of the object
	 */
	public Obstacle(String imageLink, int xpos, int ypos, int s, int w, int h) {
		setImage(new Image(imageLink, w,h, true, true));
		setX(xpos);
		setY(ypos);
		speed = s;
	}

}
