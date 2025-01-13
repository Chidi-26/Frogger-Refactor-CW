package View;

import Model.Actor;
import javafx.scene.image.Image;

/**
 * {@code BackgroundImage} class extends {@code Actor} and is essentially the background of the game
 */
public class BackgroundImage extends Actor {

	/**
	 * {@code act} method is inherited by the {@code Actor} class and is currently not used in this class
	 * @param now - represents the current time
	 */
	@Override
	public void act(long now) {
		
		
	}

	/**
	 * Constructor that sets the image for the background object to the game
	 * @param imageLink - image used for the background
	 */
	public BackgroundImage(String imageLink) {
		setImage(new Image(imageLink, 600, 800, true, true));
		
	}

}
