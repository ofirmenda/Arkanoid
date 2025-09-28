// id:322809583
// name: Ofir Menda

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Block class represents a rectangular block in the game that can be collided with.
 */
public class Block implements Collidable, Sprite {
    private Rectangle rectangle;
    private Point upperLeft;
    private int height;
    private int width;
    private Color color;

    /**
     * Constructs a Block with the specified location, dimensions, and color.
     *
     * @param x      the x-coordinate of the upper-left corner of the block.
     * @param y      the y-coordinate of the upper-left corner of the block.
     * @param height the height of the block.
     * @param width  the width of the block.
     * @param color  the color of the block.
     */
    public Block(int x, int y, int height, int width, Color color) {
        this.upperLeft = new Point(x, y);
        this.height = height;
        this.width = width;
        this.color = color;
        this.rectangle = new Rectangle(upperLeft, height, width, color);

    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(), this.width, this.height);
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(), this.width, this.height);
    }

    @Override
    public void timePassed() {
    }


    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null) {
            return currentVelocity;
        }

        System.out.println("Block collision at point: " + collisionPoint.getX() + ", " + collisionPoint.getY());

        if (this.rectangle.getLeftLine().containsPoint(collisionPoint)
                || this.rectangle.getRightLine().containsPoint(collisionPoint)) {
            System.out.println("Horizontal collision detected. Reversing X velocity.");
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (this.rectangle.getUpLine().containsPoint(collisionPoint)
                || this.rectangle.getDownLine().containsPoint(collisionPoint)) {
            System.out.println("Vertical collision detected. Reversing Y velocity.");
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }

        return currentVelocity;
    }


    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    public boolean ballColorMatch(Ball ball) {
        return this.color == ball.getColor();
    }

    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}
