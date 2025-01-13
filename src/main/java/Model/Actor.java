package Model;

import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;

import java.util.ArrayList;

/**
 * Abstract base class that extends image view, allowing images of actors to be seen on the screen
 * Implements methods to provide movement, interactions within the game world and to detect collisions between objects
 */

public abstract class Actor extends ImageView{

    /**
     * This method moves the actor object in a specified direction
     * @param dx - moves in the x direction
     * @param dy - moves in the y direction
     */
    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    /**
     * Getter method that returns the actor's world
     * @return parent of the actor
     */
    public World getWorld() {
        return (World) getParent();
    }

    /**
     * Getter method that returns the width of the actor
     */
    public double getWidth() {
        return this.getBoundsInLocal().getWidth();
    }

    /**
     * Getter method that returns the height of the actor
     * @return height
     */
    public double getHeight() {
        return this.getBoundsInLocal().getHeight();
    }

    /**
     * Getter method that gets a list of all the object that intersect/ collide with the actor
     * @param cls -class type of the object that is being checked if it has collided with the actor
     * @return the list of all colliding/intersecting objects with the actor
     * @param <A> - the actor type that is being checked for collision/intersection
     */
    public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
        ArrayList<A> someArray = new ArrayList<A>();
        for (A actor: getWorld().getObjects(cls)) {
            if (actor != this && actor.intersects(this.getBoundsInLocal())) {
                someArray.add(actor);
            }
        }
        return someArray;
    }


    public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
        ArrayList<A> someArray = new ArrayList<A>();
        for (A actor: getWorld().getObjects(cls)) {
            if (actor != this && actor.intersects(this.getBoundsInLocal())) {
                someArray.add(actor);
                break;
            }
        }
        return someArray.get(0);
    }

    /**
     * Abstract method that imputes the actors behaviour in the game world
     * @param now - represents the current time
     */
    public abstract void act(long now);

}
