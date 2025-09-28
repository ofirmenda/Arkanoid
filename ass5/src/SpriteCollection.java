// id:322809583
// name: Ofir Menda
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
/**
 * A collection of sprites that can be managed together.
 * The SpriteCollection class allows for the addition of sprites, and
 * can call methods on all sprites in the collection.
 */
public class SpriteCollection {

    private List<Sprite> sprites;
    /**
     * Constructs a new SpriteCollection.
     * Initializes an empty list to hold Sprite objects.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
    /**
     * Notifies all sprites that time has passed.
     * Calls the timePassed() method on each sprite in the collection.
     */
    // call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        for (Sprite s : sprites) {
            s.timePassed();
        }
    }
    /**
     * Draws all sprites on the given DrawSurface.
     * Calls the drawOn(DrawSurface d) method on each sprite in the collection.
     *
     * @param d the DrawSurface to draw on
     */
    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }


}

