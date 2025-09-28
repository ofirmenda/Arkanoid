// id:322809583
// name: Ofir Menda
import biuoop.DrawSurface;

import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The Paddle class represents a paddle in the game, controlled by the keyboard.
 * It implements the Sprite and Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Point upperLeft;
    private int height;
    private int width;
    private Color color;
    private GameEnvironment ge;

    /**
     * Constructs a Paddle object with the specified parameters.
     *
     * @param x      the x-coordinate of the upper-left corner of the paddle
     * @param y      the y-coordinate of the upper-left corner of the paddle
     * @param height the height of the paddle
     * @param width  the width of the paddle
     * @param color  the color of the paddle
     * @param ge     the game environment
     * @param k      the keyboard sensor
     */
    public Paddle(int x, int y, int height, int width, Color color, GameEnvironment ge, KeyboardSensor k) {
        this.upperLeft = new Point(x, y);
        this.height = height;
        this.width = width;
        this.color = color;
        this.rectangle = new Rectangle(upperLeft, height, width, color);
        this.ge = ge;
        this.keyboard = k;

    }

    /**
     * Moves the paddle to the left by a fixed amount, if within the game boundaries.
     */
    public void moveLeft() {
        if (this.upperLeft.getX() > 25) {
            this.upperLeft = new Point(this.upperLeft.getX() - 5, this.upperLeft.getY());
            this.rectangle = new Rectangle(this.upperLeft, this.height, this.width, this.color);
        }
    }

    /**
     * Moves the paddle to the right by a fixed amount, if within the game boundaries.
     */

    public void moveRight() {
        if (this.upperLeft.getX() < 800 - this.width - 25) {
            this.upperLeft = new Point(this.upperLeft.getX() + 5, this.upperLeft.getY());
            this.rectangle = new Rectangle(this.upperLeft, this.height, this.width, this.color);
        }
    }

    /**
     * Updates the paddle's position based on the current state of the keyboard.
     */
    // Sprite
    @Override
    public void timePassed() {

        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }

    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(), this.width, this.height);
        d.setColor(Color.black);
        d.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(), this.width, this.height);
    }

    // Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {

        if (collisionPoint == null) {
            return currentVelocity;
        }
        if (this.rectangle.getLeftLine().containsPoint(collisionPoint)
                || (this.rectangle.getRightLine().containsPoint(collisionPoint))) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (this.rectangle.getUpLine().containsPoint(collisionPoint)
                || (this.rectangle.getDownLine().containsPoint(collisionPoint))) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }

        return currentVelocity;
    }

    // Add this paddle to the game.
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Returns the color of the paddle.
     *
     * @return the paddle's color
     */

    public Color getColor() {
        return color;
    }

    /**
     * Returns the upper-left point of the paddle.
     *
     * @return the paddle's upper-left point
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
}
