// id:322809583
// name: Ofir Menda


import biuoop.GUI;

import java.util.List;


/**
 * Represents a line segment between two points.
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * Constructs a Line object with specified start and end points.
     *
     * @param start The starting point of the line.
     * @param end   The ending point of the line.
     */
    // constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a Line object with specified coordinates for start and end points.
     *
     * @param x1 The x-coordinate of the starting point.
     * @param y1 The y-coordinate of the starting point.
     * @param x2 The x-coordinate of the ending point.
     * @param y2 The y-coordinate of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Calculates and returns the length of the line segment.
     *
     * @return The length of the line segment.
     */
    // Returns the length of the line
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle point of the line segment.
     *
     * @return The middle point of the line segment.
     */
    // Returns the middle point of the line
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * Returns the starting point of the line segment.
     *
     * @return The starting point of the line segment.
     */
    // Returns the start point of the line
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }

    /**
     * Returns the ending point of the line segment.
     *
     * @return The ending point of the line segment.
     */
    // Returns the end point of the line
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }

    /**
     * Calculates and returns the slope of the line segment.
     *
     * @return The slope of the line segment.
     */
    // Returns the slope of the line
    public double getM() {
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * Calculates and returns the y-intercept of the line segment.
     *
     * @return The y-intercept of the line segment.
     */
    //Return the x value of the intercept with y of the line
    public double getB() {
        return this.start.getY() - (this.getM() * this.start.getX());
    }

    /**
     * Checks if this line segment is equal to another line segment.
     *
     * @param other The other line segment to compare with.
     * @return True if the two line segments are equal, false otherwise.
     */
    // Returns if the two line are equal
    public boolean isEqual(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }

    /**
     * Checks if a point is contained within the line segment.
     *
     * @param point The point to check for containment.
     * @return True if the point is contained within the line segment, false otherwise.
     */
    //Return if the point is contained in the line
    boolean containsPoint(Point point) {
        double x = point.getX();
        double y = point.getY();
        double startX = this.start.getX();
        double startY = this.start.getY();
        double endX = this.end.getX();
        double endY = this.end.getY();

        // set boolean variables to check if the x or y values are within the needed range
        boolean xInRange = (x >= Math.min(startX, endX)) && (x <= Math.max(startX, endX));
        boolean yInRange = (y >= Math.min(startY, endY)) && (y <= Math.max(startY, endY));

        boolean inRange = xInRange && yInRange;

        // Check if the line is vertical
        if (Math.abs(startX - endX) < 1e-6) {
            // For vertical line, check if the x-coordinate of the point matches the line's x-coordinate
            return inRange && (Math.abs(x - startX) < 1e-6);
        } else {
            // For non-vertical lines, calculate the slope and intercept
            double m = this.getM();
            double b = this.getB();
            return inRange && (Math.abs(y - (m * x + b)) < 1e-6);
        }
    }

    /**
     * Checks if this line segment is contained within another line segment.
     *
     * @param other The other line segment to check for containment.
     * @return True if this line segment is contained within the other, false otherwise.
     */

    // Returns if the line is contained in the other line.
    public boolean isContained(Line other) {
        double m1 = this.getM();
        double m2 = other.getM();
        return (m1 == m2)
                && (this.containsPoint(other.start) || (this.containsPoint(other.end)))
                && (this.start != other.end && this.end != other.start);
    }

    /**
     * Checks if this line segment intersects with another line segment.
     *
     * @param other The other line segment to check for intersection.
     * @return True if the two line segments intersect, false otherwise.
     */
    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        if (this.isEqual(other) || this.isContained(other)) {
            return true;
        }
        return this.intersectionWith(other) != null;
    }

    /**
     * Checks if this line segment intersects with two other line segments.
     *
     * @param other1 The first other line segment to check for intersection.
     * @param other2 The second other line segment to check for intersection.
     * @return True if this line segment intersects with both other line segments, false otherwise.
     */
    // Returns true if this 2 lines intersect with this line, false otherwise
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }

    /**
     * Calculates and returns the intersection point of this line segment with another.
     *
     * @param other The other line segment to find intersection with.
     * @return The intersection point if the line segments intersect, null otherwise.
     */
    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();

        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        double m = ((x1 - x2) * (y3 - y4))
                - ((y1 - y2) * (x3 - x4));
        if (m == 0) {
            return null;
        }

        double xNumerator = ((x1 * y2 - y1 * x2)
                * (x3 - x4)) - ((x1 - x2) * (x3 * y4 - y3 * x4));
        double yNumerator = ((x1 * y2 - y1 * x2)
                * (y3 - y4)) - ((y1 - y2) * (x3 * y4 - y3 * x4));

        double x = xNumerator / m;
        double y = yNumerator / m;
        Point intersection = new Point(x, y);
        if (isPointOn(intersection, this) && isPointOn(intersection, other)) {
            return intersection;
        } else {
            return null;
        }
    }

    // Helper method to check if a point is on a line segment
    private boolean isPointOn(Point point, Line line) {
        double minimalX = Math.min(line.start.getX(), line.end.getX());
        double maximalX = Math.max(line.start.getX(), line.end.getX());
        double minimalY = Math.min(line.start.getY(), line.end.getY());
        double maximalY = Math.max(line.start.getY(), line.end.getY());

        return point.getX() >= minimalX && point.getX() <= maximalX
                && point.getY() >= minimalY && point.getY() <= maximalY;
    }


    /**
     * Checks if this line segment is equal to another line segment.
     *
     * @param other The other line segment to compare with.
     * @return True if the two line segments are equal, false otherwise.
     */
    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }

    /**
     * Checks if this line segment is equal to another line segment.
     *
     * @param rect to find intersection with
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     */
    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> list = rect.intersectionPoints(new Line(this.start(), this.end()));
        if (list == null || list.isEmpty()) {
            return null;
        }
        Point closestToStart = null;
        double minDistance = Double.MAX_VALUE;
        for (Point p : list) {
            double curDistance = p.distance(this.start);
            if (closestToStart == null || curDistance < minDistance) {
                closestToStart = new Point(p.getX(), p.getY());
                minDistance = curDistance;
            }
        }

        return new Point(closestToStart.getX(), closestToStart.getY());
    }


}
