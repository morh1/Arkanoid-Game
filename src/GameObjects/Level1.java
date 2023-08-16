package GameObjects;

import Geometric.Point;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 1.
 */
public class Level1 implements LevelInformation {
    private static final int NUM_OF_BALLS = 1;
    private static final double SPEED = 5;
    private static final int PADDLE_SPEED = 10;
    private static final int PADDLE_WIDTH = 120;
    private static final String LEVEL_NAME = "LEVE-1";
    /**
     * returning the number of balls.
     * @return NUM_OF_BALLS
     */
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }
    /**
     * returning list of velocities.
     * @return vlist
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vlist = new ArrayList<>();
        Velocity velocity = new Velocity(1, 1);
        vlist.add(velocity.fromAngleAndSpeed(0, SPEED));
        return vlist;
    }
    /**
     * returning the paddle speed.
     * @return PADDLE_SPEED
     */
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }
    /**
     * returning the paddle width.
     * @return PADDLE_WIDTH
     */
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }
    /**
     * returning the level name.
     * @return LEVEL_NAME
     */
    public String levelName() {
        return LEVEL_NAME;
    }
    /**
     * returning backgroundSprite.
     * @return backgroundSprite
     */
    public Sprite getBackground() {
        Sprite backgroundSprite = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                for (int i = 0; i < 60; i++) {
                    d.setColor(new Color(150 + i / 2, 48 + i * 3, 195 + i));
                    d.fillRectangle(0, 600 - i * 10, 800, 10);
                }
                //d.setColor(new Color(200,20,198));
                d.setColor(Color.black);
                d.drawCircle(400, 210, 100);
                d.drawCircle(400, 210, 70);
                d.drawCircle(400, 210, 40);
                d.drawLine(400, 240, 400, 320);
                d.drawLine(400, 190, 400, 100);
                d.drawLine(430, 215, 510, 215);
                d.drawLine(370, 215, 310, 215);
            }

            @Override
            public void timePassed() {

            }
        };
        return backgroundSprite;
    }
    /**
     * returning list of the blocks.
     * @return backgroundSprite
     */
    public List<Block> blocks() {
        Point point = new Point(385, 200);
        Block block = new Block(point, 30, 30);
        java.awt.Color color = new Color(150, 40, 160);
        block.setColor(color);
        List<Block> blocksList = new ArrayList<>();
        blocksList.add(block);
        return blocksList;
    }
    /**
     * returning the number of blocks we need to remove.
     * @return (int)
     */
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
