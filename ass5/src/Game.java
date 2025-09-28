// id:322809583
// name: Ofir Menda

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Game class represents a basic game of Arkanoid.
 * It manages the game's sprites, environment, and main game loop.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;

    /**
     * Adds a Collidable object to the game environment.
     *
     * @param c the Collidable to add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a Sprite object to the game.
     *
     * @param s the Sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initializes a new game: creates the Blocks, Ball, and Paddle,
     * and adds them to the game.
     */
    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Arkanoid", 800, 600);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();

        this.sleeper = new Sleeper();
        List<Ball> balls = new ArrayList<>();
        balls.add(new Ball(500, 500, 7, Color.white, 575, 775, this.environment));
        balls.add(new Ball(100, 500, 7, Color.white, 575, 775, this.environment));
        for (Ball b : balls) {
            b.setVelocity(4, 4);
            b.addToGame(this);
        }
        List<Block> blocks = new ArrayList<>();
        addBordersBlocks(blocks);
        addColoredBlocks(blocks);
        for (Block b : blocks) {
            b.addToGame(this);
        }

        Paddle paddle = new Paddle(375, 550, 25, 100, Color.yellow, this.environment, keyboard);
        paddle.addToGame(this);
    }

    /**
     * Runs the game: starts the animation loop.
     */
    // Run the game -- start the animation loop.
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

    }

    /**
     * Adds colored blocks to the game.
     *
     * @param blocks the list of blocks to add to
     */
    public void addColoredBlocks(List<Block> blocks) {
        int start = 725;
        for (int i = 0; i < 12; i++) {
            blocks.add(new Block(start, 100, 25, 50, Color.gray));
            start -= 50;
        }
        start = 725;
        for (int i = 0; i < 11; i++) {
            blocks.add(new Block(start, 125, 25, 50, Color.red));
            start -= 50;
        }
        start = 725;
        for (int i = 0; i < 10; i++) {
            blocks.add(new Block(start, 150, 25, 50, Color.yellow));
            start -= 50;
        }
        start = 725;
        for (int i = 0; i < 9; i++) {
            blocks.add(new Block(start, 175, 25, 50, Color.blue));
            start -= 50;
        }
        start = 725;
        for (int i = 0; i < 8; i++) {
            blocks.add(new Block(start, 200, 25, 50, Color.pink));
            start -= 50;
        }
        start = 725;
        for (int i = 0; i < 7; i++) {
            blocks.add(new Block(start, 225, 25, 50, Color.green));
            start -= 50;
        }

    }

    /**
     * Adds border blocks to the game.
     *
     * @param blocks the list of blocks to add to
     */
    public void addBordersBlocks(List<Block> blocks) {
        blocks.add(new Block(0, 0, 25, 800, Color.gray)); //up Border
        blocks.add(new Block(25, 575, 25, 750, Color.gray)); //down Border
        blocks.add(new Block(0, 25, 575, 25, Color.gray)); //Left Border
        blocks.add(new Block(775, 25, 575, 25, Color.gray)); //Right Border
    }

    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);

    }

    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}

