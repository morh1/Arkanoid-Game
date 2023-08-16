package GameObjects;
import Geometric.Point;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Level 2.
 */
public class Level2 implements LevelInformation {
    private static final int NUM_OF_BALLS = 7;
    private static final int PADDLE_SPEED = 10;
    private static final int PADDLE_WIDTH = 200;
    private static final String LEVEL_NAME = "LEVE-2";
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
        Velocity velocity = new Velocity(4, 4);
        for (int i = 0; i < 7; i++) {
            vlist.add(velocity);
        }
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
           private int x;

            @Override
            public void drawOn(DrawSurface d) {
                for (int i = 0; i < 60; i++) {
                    d.setColor(new Color(53 + i * 2, 123 + i * 2, 255));
                    d.fillRectangle(0, 600 - i * 10, 800, 10);
                }
                d.setColor(new Color(250, 250, 100));
                d.fillCircle(150, 150, 100);
                for (int i = 0; i < 100; i++) {
                    d.drawLine(150, 150, 680 - i * 7, 300);
                }
                d.setColor(new Color(255, 210, 50));
                d.fillCircle(150, 150, 90);
                d.setColor(new Color(160, 196, 255));
                d.fillCircle(250 + x, 400, 25);
                d.fillCircle(280 + x, 400, 25);
                d.fillCircle(300 + x, 420, 25);
                d.fillCircle(270 + x, 420, 25);
                d.fillCircle(235 + x, 420, 25);
                d.setColor(new Color(223, 231, 253));
                d.fillCircle(550 - x, 500, 25);
                d.fillCircle(580 - x, 500, 25);
                d.fillCircle(600 - x, 520, 25);
                d.fillCircle(570 - x, 520, 25);
                d.fillCircle(535 - x, 520, 25);
                x++;
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
        int indexCounter = -1;
        List<Block> blocksList = new ArrayList<>();
        Color[] colors = {new Color(58, 134, 255), new Color(131, 56, 236),
                new Color(209, 0, 209), new Color(255, 0, 110), new Color(251, 86, 7),
                new Color(229, 0, 164), new Color(255, 190, 11)};
        for (int i = 0; i < 14; i++) {
            if (i % 2 == 0) {
                indexCounter++;
            }
            Point point = new Point(724 - i * 54, 280);
            Block block = new Block(point, 54, 30);
            block.setColor(colors[indexCounter]);
            blocksList.add(block);
        }

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

