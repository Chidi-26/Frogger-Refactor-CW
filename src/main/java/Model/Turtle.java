package Model;

import javafx.scene.image.Image;

/**
 * {@code Turtle} class extends {@code Actor}
 * Implements the turtle animation behaviour, position, image, speed direction and size
 */
public class Turtle extends Actor{
	Image turtle1;
	Image turtle2;
	Image turtle3;
	public int speed;

	/**
	 * {@code act} method is inherited by the {@code Actor} class
	 * Implements the behaviour of the turtle
	 * Updates the turtle based on the current time
	 * @param now - represents the current time
	 */
	@Override
	public void act(long now) {

				if (now/900000000  % 3 ==0) {
					setImage(turtle2);
					
				}
				else if (now/900000000 % 3 == 1) {
					setImage(turtle1);
					
				}
				else if (now/900000000 %3 == 2) {
					setImage(turtle3);
					
				}
			
		move(speed , 0);
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -75 && speed<0)
			setX(600);
	}

	/**
	 * Constructor that imputes the different the 3 different turtle animations, their position, speed and size
	 * @param xpos - sets the x coordinate of the turtle
	 * @param ypos - sets the y coordinate of the turtle
	 * @param s - sets the speed of the turtle
	 * @param w - sets the width of the turtle
	 * @param h - sets the height of the turtle
	 */
	public Turtle(int xpos, int ypos, int s, int w, int h) {
		turtle1 = new Image("/TurtleAnimation1.png", w, h, true, true);
		turtle2 = new Image("/TurtleAnimation2.png", w, h, true, true);
		turtle3 = new Image("/TurtleAnimation3.png", w, h, true, true);
		setX(xpos);
		setY(ypos);
		speed = s;
		setImage(turtle2);
	}
}
