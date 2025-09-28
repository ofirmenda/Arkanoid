// id:322809583
// name: Ofir Menda
/**
 * The {@code CollisionInfo} class holds information about a collision event,
 * including the point of collision and the collidable object involved.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidable;
    /**
     * Constructs a new {@code CollisionInfo} with the specified collision point
     * and collidable object.
     *
     * @param collisionPoint the point where the collision occurred
     * @param collidable the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collidable = collidable;
    }
    /**
     * Returns the point where the collision occurred.
     *
     * @return the collision point
     */
    public Point getCollisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object
     */
    public Collidable getCollidable() {
        return this.collidable;
    }

}
