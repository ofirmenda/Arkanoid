// id:322809583
// name: Ofir Menda
import biuoop.DrawSurface;
/**
 * A Sprite is an object that can be drawn on the screen, and can be notified that time has passed.
 * Implementing classes should define how the sprite is drawn and how it behaves over time.
 */
public interface Sprite {
    /**
     * Draws the sprite onto the given DrawSurface.
     *
     * @param d the DrawSurface to draw the sprite on.
     */
    // draw the sprite to the screen
    void drawOn(DrawSurface d);
    /**
     * Notifies the sprite that time has passed, allowing it to update its state.
     * Implementing classes should define the behavior for what happens as time passes.
     */
    // notify the sprite that time has passed
    void timePassed();
    /**
     * Adds the sprite to the given game.
     * Implementing classes should define how the sprite is added to the game.
     *
     * @param g the game to add the sprite to.
     */
    void addToGame(Game g);
}
