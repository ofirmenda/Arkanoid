// id:322809583
// name: Ofir Menda
import java.util.List;
import java.util.ArrayList;

/**
 * Represents the environment where collidable objects interact and collide.
 */
public class GameEnvironment {

    private List<Collidable> collidables;

    /**
     * Constructs a new GameEnvironment with an empty list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds a collidable object to the environment.
     *
     * @param c the collidable object to be added
     */
    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Finds the closest collision point with a collidable object along a given trajectory line.
     *
     * @param trajectory the trajectory line along which collision is checked
     * @return a CollisionInfo object containing information about the closest collision,
     * or null if no collision occurs
     */
    // Assume an object moving from line.start() to line.end()
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        double distance = Double.MAX_VALUE;
        Point closestCollisionPoint = null;
        Collidable closestCollisionRectangle = null;
        Point startPoint = trajectory.start();

        for (Collidable c : collidables) {
            Rectangle curCollisionRectangle = c.getCollisionRectangle();
            if (curCollisionRectangle != null) {
                Point curCollisionPoint = trajectory.closestIntersectionToStartOfLine(curCollisionRectangle);
                if (curCollisionPoint != null) {
                    double curDistance = startPoint.distance(curCollisionPoint);
                    if (curDistance < distance) {
                        distance = curDistance;
                        closestCollisionPoint = new Point(curCollisionPoint.getX(), curCollisionPoint.getY());
                        closestCollisionRectangle = c;
                    }
                }
            }
        }

        if (closestCollisionPoint == null) {
            System.out.println("No collision detected.");
            return null;
        }

        System.out.println("Closest collision at point: " + closestCollisionPoint.getX() + ", " + closestCollisionPoint.getY());
        return new CollisionInfo(closestCollisionPoint, closestCollisionRectangle);
    }

}
