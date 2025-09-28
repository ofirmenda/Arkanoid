// id:322809583
// name: Ofir Menda
/**
 * The Collidable interface represents objects that can be collided with in a game environment.
 * Objects implementing this interface must be able to provide their collision shape and
 * respond to collision events with a new velocity.
 */
public interface Collidable {
    // Return the "collision shape" of the object.
    /**
     * Gets the collision rectangle of the object.
     * This method provides the shape of the object that can be collided with.
     *
     * @return the collision rectangle of the object.
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    /**
     * Notifies the object that it has been collided with at a specific point with a given velocity.
     * This method is called to handle the collision event and calculate the new velocity
     * after the hit based on the force the object inflicted.
     *
     * @param collisionPoint the point at which the collision occurred.
     * @param currentVelocity the current velocity of the object before the collision.
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}
