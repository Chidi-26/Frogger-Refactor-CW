package Model;

import javafx.scene.image.Image;

/**
 * {@code WetTurtle} class is a wet turtle in the app and has the ability to sink causing the frog to die
 */

public class WetTurtle extends Actor{
	Image t1;
	Image t2;
	Image t3;
	Image t4;
	int speed;
	boolean sunk = false;

	/**
	 * {@code act} method is inherited by the {@code Actor} class
	 * Defines the behaviour of the wet turtle
	 * Updates the wet turtle based on the current time
	 * @param now - represents the current time
	 */
	@Override
	public void act(long now) {

				if (now/900000000  % 4 ==0) {
					setImage(t2);
					sunk = false;
					
				}
				else if (now/900000000 % 4 == 1) {
					setImage(t1);
					sunk = false;
				}
				else if (now/900000000 %4 == 2) {
					setImage(t3);
					sunk = false;
				} else if (now/900000000 %4 == 3) {
					setImage(t4);
					sunk = true;
				}
			
		move(speed , 0);
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -75 && speed<0)
			setX(600);
	}

	/**
	 * Constructor which sets the wet turtles position, size and speed
	 * @param xpos - setts x coordinates for the wet turtle
	 * @param ypos - setts y coordinates for the wet turtle
	 * @param s - setts speed for the wet turtle
	 * @param w - sets the width for the wet turtle
	 * @param h - sets the height of the wet turtle
	 */
	public WetTurtle(int xpos, int ypos, int s, int w, int h) {
		t1 = new Image("/TurtleAnimation1.png", h, w, true, true);
		t2 = new Image("/TurtleAnimation2Wet.png", h, w, true, true);
		t3 = new Image("/TurtleAnimation3Wet.png", w, h, true, true);
		t4 = new Image("/TurtleAnimation4Wet.png", w, h, true, true);
		setX(xpos);
		setY(ypos);
		speed = s;
		setImage(t2);
	}

	/**
	 * {@code isSunk} method checks if the wet turtle has sunk
	 * @return true if the turtle has sunk and false if it hasn't
	 */
	public boolean isSunk() {
		return sunk;
	}
}
