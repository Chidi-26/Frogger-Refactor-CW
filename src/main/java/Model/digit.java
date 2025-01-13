package Model;

import javafx.scene.image.Image;

/**
 * {@code digit} class extends {@code Actor} class
 * Represents each digit as a graphical image and stores it a specific place
 */
public class digit extends Actor{
	int dim;
	Image im1;

	/**
	 * {@code act} method is inherited from the {@code Actor} class
	 * @param now - represents the current time
	 */
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Constructor that imputes digits but shows the player a graphical image and stores/places the image in a place
	 * @param n - digit number value
	 * @param dim - dimension of the image
	 * @param x - sets X coordinates of the image
	 * @param y - sets Y coordinates of the image
	 */
	public digit(int n, int dim, int x, int y) {
		im1 = new Image("/"+n+".png", dim, dim, true, true);
		setImage(im1);
		setX(x);
		setY(y);
	}
	
}
