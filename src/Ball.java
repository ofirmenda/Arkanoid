// id:322809583
// name: Ofir Menda

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The Ball class represents a moving ball in a two-dimensional space.
 */
public class Ball implements Sprite {
    private Point center;
    private final int r;
    private Color color;
    private Velocity velocity;
    private final int borderHeight;
    private final int borderWidth;
    private GameEnvironment ge;

    /**
     * Constructor for creating a Ball object with a specified position, radius, and color.
     *
     * @param x            The x-coordinate of the center of the ball.
     * @param y            The y-coordinate of the center of the ball.
     * @param r            The radius of the ball.
     * @param color        The color of the ball.
     * @param borderHeight The borderHeight of the ball.
     * @param borderWidth  The borderWidth of the ball.
     * @param ge           The GameEnvironment of the ball.
     */
    // constructor
    public Ball(int x, int y, int r, java.awt.Color color, int borderHeight, int borderWidth, GameEnvironment ge) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.borderHeight = borderHeight;
        this.borderWidth = borderWidth;
        this.ge = ge;
    }

    // accessors:

    /**
     * Gets the x-coordinate of the center of the ball.
     *
     * @return The x-coordinate of the center of the ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets the y-coordinate of the center of the ball.
     *
     * @return The y-coordinate of the center of the ball.
     */
    public int getY() {
        return (int) this.center.getY();

    }

    /**
     * Gets the radius of the ball.
     *
     * @return The radius of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets the color of the ball.
     *
     * @return The color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface is the drawing surface
     */
    // draw the ball on the given DrawSurface
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.r);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param v is the velocity of the ball
     */


    // set velocity
    public void setVelocity(Velocity v) {
        this.velocity = v;

    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param dx is x value of the added step
     * @param dy is y value of the added step
     */
    // set velocity with dx and dy
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return the velocity of the ball.
     */
    // get velocity
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Updates the next step of the ball.
     */
    // Update the balls' next step according to its velocity
    public void moveOneStep() {
        Velocity currentVelocity = this.getVelocity();
        double nextX = this.center.getX() + (2 * currentVelocity.getDx());
        double nextY = this.center.getY() + (2 * currentVelocity.getDy());

        // Check for collisions with the screen boundaries
        if (nextX - this.r <= 25 || nextX + this.r >= this.borderWidth) {
            System.out.println("Collision with screen boundary detected on X-axis.");
            this.velocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (nextY - this.r <= 25 || nextY + this.r >= this.borderHeight) {
            System.out.println("Collision with screen boundary detected on Y-axis.");
            this.velocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }

        Line trajectory = new Line(this.center.getX(), this.center.getY(), nextX, nextY);
        CollisionInfo collisionInfo = ge.getClosestCollision(trajectory);

        if (collisionInfo == null) {
            this.center = currentVelocity.applyToPoint(this.center);
            return;
        }

        Collidable collidable = collisionInfo.getCollidable();
        Point collisionPoint = collisionInfo.getCollisionPoint();

        System.out.println("Collision detected at point: " + collisionPoint.getX() + ", " + collisionPoint.getY());
        System.out.println("Collidable object: " + collidable);

        this.velocity = collidable.hit(collisionPoint, currentVelocity);
        this.center = this.velocity.applyToPoint(this.center);
    }



}








