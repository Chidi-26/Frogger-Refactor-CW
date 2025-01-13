package Model;


import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;


/**
 * {@code World} is an abstract class that extends JavaFX {@code Pane} and it represents the game world
 * Acts as a container to contain all the game elements
 */
public abstract class World extends Pane {
    private AnimationTimer t;

    /**
     * Constructor that handles keys being presses and released while relating them to the actors and scene
     */
    public World() {
    	
    	sceneProperty().addListener(new ChangeListener<Scene>() {

			@Override
			public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
				if (newValue != null) {
					newValue.setOnKeyReleased(new EventHandler<KeyEvent>() {

						@Override
						public void handle(KeyEvent event) {
							if(getOnKeyReleased() != null) 
								getOnKeyReleased().handle(event);
							List<Actor> myActors = getObjects(Actor.class);
							for (Actor anActor: myActors) {
								if (anActor.getOnKeyReleased() != null) {
									anActor.getOnKeyReleased().handle(event);
								}
							}
						}
						
					});
					
					newValue.setOnKeyPressed(new EventHandler<KeyEvent>() {

						@Override
						public void handle(KeyEvent event) {
							if(getOnKeyPressed() != null) 
								getOnKeyPressed().handle(event);
							List<Actor> myActors = getObjects(Actor.class);
							for (Actor anActor: myActors) {
								if (anActor.getOnKeyPressed() != null) {
									anActor.getOnKeyPressed().handle(event);
								}
							}
						}
						
					});
				}
				
			}
    		
		});
    }

    /**
     * {@code createTimer} method creates a timer that updates the world and everything in it
     */
    public void createTimer() {
        t = new AnimationTimer() {
            @Override
            public void handle(long now) {
                act(now);
                List<Actor> actors = getObjects(Actor.class);
                
                for (Actor anActor: actors) {
                	anActor.act(now);
                }
      
            }
        };
    }

    /**
     * {@code start} method starts the timer and starts the game
     */
    public void start() {
    	createTimer();
        t.start();
    }

    /**
     * {@code stop} method stops the game by stopping the timer
     */
    public void stop() {
        if(t != null){
            t.stop();
        }
    }

    /**
     * {@code add} method adds the actor to the world
     * @param actor - current actor that is being added to the world
     */
    public void add(Actor actor) {
        getChildren().add(actor);
    }

    /**
     * {@code getObjects} is a getter method that retries the list of specific class that is in the world
     * @param cls - class type of objects
     * @return list of the object of a specified class
     * @param <A> - Type of object that is being gotten
     */

    public <A extends Actor> List<A> getObjects(Class<A> cls) {
        ArrayList<A> someArray = new ArrayList<A>();
        for (Node n: getChildren()) {
            if (cls.isInstance(n)) {
                someArray.add((A)n);
            }
        }
        return someArray;
    }

    /**
     * Abstract method that each in game time tick, defines the behaviour of the world
     * @param now - represents present time
     */
    public abstract void act(long now);
}
