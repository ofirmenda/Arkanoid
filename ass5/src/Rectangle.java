
// id:322809583
// name: Ofir Menda
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**

 The Rectangle class represents a rectangle shape with a specified

 upper-left corner, height, width, and color. It provides methods to

 access its properties and calculate intersection points with a line.
 */
public class Rectangle {
    private Point upperLeft;
    private double height;
    private double width;
    private Color color;
    /**

     Creates a new rectangle with specified location, width, height, and color.
     @param upperLeft the upper-left point of the rectangle.
     @param height the height of the rectangle.
     @param width the width of the rectangle.
     @param color the color of the rectangle.
     */
    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double height, double width, Color color) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
        this.color = color;
    }
    /**

     Returns the width of the rectangle.
     @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }
    /**

     Returns the height of the rectangle.
     @return the height of the rectangle.
     */

    public double getHeight() {
        return this.height;
    }
    /**

     Returns the color of the rectangle.
     @return the color of the rectangle.
     */
    public Color getColor() {
        return color;
    }
    /**

     Returns the upper-left point of the rectangle.
     @return the upper-left point of the rectangle.
     */
    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**

     Returns the upper-right corner point of the rectangle.
     @return the upper-right corner point of the rectangle.
     */

    public Point getRightUpCorner() {
        return new Point(upperLeft.getX() + getWidth(), upperLeft.getY());
    }
    /**

     Returns the lower-right corner point of the rectangle.
     @return the lower-right corner point of the rectangle.
     */

    public Point getRightDownCorner() {
        return new Point(upperLeft.getX() + this.getWidth(), upperLeft.getY() + this.getHeight());
    }
    /**

     Returns the lower-left corner point of the rectangle.
     @return the lower-left corner point of the rectangle.
     */
    public Point getLeftDownCorner() {
        return new Point(upperLeft.getX(), upperLeft.getY() + this.getHeight());
    }
    /**

     Returns the top edge of the rectangle as a line.
     @return the top edge line of the rectangle.
     */
    public Line getUpLine() {
        return new Line(this.getUpperLeft(), this.getRightUpCorner());
    }
    /**

     Returns the bottom edge of the rectangle as a line.
     @return the bottom edge line of the rectangle.
     */

    public Line getDownLine() {
        return new Line(this.getLeftDownCorner(), this.getRightDownCorner());
    }
    /**

     Returns the left edge of the rectangle as a line.
     @return the left edge line of the rectangle.
     */

    public Line getLeftLine() {
        return new Line(this.getLeftDownCorner(), this.getUpperLeft());
    }
    /**

     Returns the right edge of the rectangle as a line.
     @return the right edge line of the rectangle.
     */
    public Line getRightLine() {
        return new Line(this.getRightDownCorner(), this.getRightUpCorner());
    }
    /**

     Returns a (possibly empty) list of intersection points

     with the specified line.

     @param line the line to check for intersections with the rectangle.

     @return a list of intersection points with the specified line.
     */
    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<>();
        Line lineUp = this.getUpLine();
        Line lineDown = this.getDownLine();
        Line lineLeft = this.getLeftLine();
        Line lineRight = this.getRightLine();
        Point intersectionUp = line.intersectionWith(lineUp);
        Point intersectionDown = line.intersectionWith(lineDown);
        Point intersectionLeft = lineLeft.intersectionWith(line);
        Point intersectionRight = lineRight.intersectionWith(line);
        //Up
        if (intersectionUp != null) {
            list.add(intersectionUp);
        }
        //Down
        if (intersectionDown != null) {
            list.add(intersectionDown);
        }

        if (intersectionLeft != null) {
            list.add(intersectionLeft);

        }
        if (intersectionRight != null) {
            list.add(intersectionRight);
        }
        return list;
    }


}
