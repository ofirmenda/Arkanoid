
// id:322809583
// name: Ofir Menda

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

/**
 * The Point class represents a point in a 2D coordinate system.
 * It provides methods to calculate the distance to another point and to check for equality with another point.
 */
public class Point {
    //Fields
    private final double x;
    private final double y;
    private static final double EPSILON = 1e-9;
    //Constructor
    /**
     * Constructs a new Point with the specified coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between this point and the other point
     */
    //Methods
    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        return sqrt(pow(this.x - other.x, 2) + pow(this.y - other.y, 2));
    }

    /**
     * Checks if this point is equal to another point.
     * Two points are considered equal if their x and y coordinates are the same.
     *
     * @param other the other point to compare
     * @return true if the points are equal, false otherwise
     */
    // equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return (Math.abs(this.x - other.x) < EPSILON) && (Math.abs(this.y - other.y) < EPSILON);
    }


    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    // Return the x and y values of this point
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return y;
    }
}