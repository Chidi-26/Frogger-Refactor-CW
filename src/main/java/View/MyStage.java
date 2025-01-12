package View;

import java.util.Objects;

import Model.World;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * {@code MyStage} class extends {@code MyWorld} represents the {@code primaryStage} in javaFX which enable all the elements to be added together
 *
 */
public class MyStage extends World {
	MediaPlayer mediaPlayer;

	/**
	 * {@code act} method is inherited from {@code World} but is unused
	 * @param now - represents present time
	 */
	@Override
	public void act(long now) {
		
	}

	/**
	 * Constructor that initializes the stage
	 */
	public MyStage() {

	}

	/**
	 * {@code playMusic} method plays background music in a continual loop
	 */
	public void playMusic() {
		String musicFile = Objects.requireNonNull(getClass().getResource("/Snake_Charmer.mp3")).toExternalForm();
		Media sound = new Media(musicFile);
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}

	/**
	 * {@code stopMusic} method stops the music from being played, breaking it out of the loop
	 */
	public void stopMusic() {
		mediaPlayer.stop();
	}

}
