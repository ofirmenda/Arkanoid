// id:322809583
// name: Ofir Menda

/**
 * A class representing a velocity.
 */
public class Velocity {
    // constructor
    private final double dx;
    private final double dy;

    /**
     * Constructs a velocity with given components.
     *
     * @param dx The velocity component in the x direction.
     * @param dy The velocity component in the y direction.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets the velocity component in the x direction.
     *
     * @return The velocity component in the x direction.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets the velocity component in the y direction.
     *
     * @return The velocity component in the y direction.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Applies this velocity to a point, resulting in a new point with updated coordinates.
     *
     * @param p The point to which the velocity is applied.
     * @return A new point with updated coordinates after applying this velocity.
     */
    // return the updated point with the added dx and dy
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        return new Point(newX, newY);
    }

    /**
     * Creates a velocity from given angle and speed.
     *
     * @param angle The angle of the velocity vector in degrees.
     * @param speed The speed of the velocity.
     * @return The velocity with components calculated from angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Convert to radians.
        double angleRadians = Math.toRadians(angle);

        // Calculate dx and dy.
        double dx = speed * Math.cos(angleRadians);
        double dy = speed * Math.sin(angleRadians);
        return new Velocity(dx, dy);
    }
    /**
     * Calculates the speed given the velocity components.
     *
     * @return The speed of the velocity.
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }
}
