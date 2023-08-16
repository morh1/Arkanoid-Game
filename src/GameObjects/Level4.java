package GameObjects;

import Geometric.Point;
import biuoop.DrawSurface;
import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Level4 class.
 */
public class Level4 implements LevelInformation {
    private static final int NUM_OF_BALLS = 1;
    private static final double SPEED = 4;
    private static final int PADDLE_SPEED = 10;
    private static final int PADDLE_WIDTH = 120;
    private static final String LEVEL_NAME = "LEVE-4";
    private int counter = 1;

    /**
     * returning the number of balls.
     *
     * @return NUM_OF_BALLS
     */
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    /**
     * returning list of velocities.
     *
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
     *
     * @return PADDLE_SPEED
     */
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     * returning the paddle width.
     *
     * @return PADDLE_WIDTH
     */
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * returning the level name.
     *
     * @return LEVEL_NAME
     */
    public String levelName() {
        return LEVEL_NAME;
    }

    /**
     * returning backgroundSprite.
     *
     * @return backgroundSprite
     */
    public Sprite getBackground() {
        Sprite backgroundSprite = new Sprite() {
            private int y, randX, randY;

            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.black);
                d.fillRectangle(0, 0, 800, 600);
                Random rand = new Random();
                d.setColor(Color.white);
                for (int i = 0; i < 50; i++) {
                    if (counter > 3) {
                        randX = rand.nextInt(800);
                        randY = rand.nextInt(600);
                    }
                    d.fillCircle(randX, randY, 1);
                }
                counter++;
                int[] xpoints = {25, 145, 25, 145, 25};
                int[] ypoints = {25, 25, 145, 145, 25};
                int npoints = 5;
                // x coordinates of vertices
                int[] x1 = {300, 300, 280};
                int[] x2 = {350, 350, 370};
                // y coordinates of vertices
                int[] y1 = {440 + y, 420 + y, 460 + y};
                int[] y2 = {440 + y, 420 + y, 460 + y};
                // number of vertices
                int numberofpoints = 3;
                d.setColor(new Color(114, 8, 10));
                d.fillCircle(325, 400 + y, 20);
                Polygon p1 = new Polygon(x1, y1, numberofpoints);
                d.fillPolygon(p1);
                Polygon p2 = new Polygon(x2, y2, numberofpoints);
                d.fillPolygon(p2);
                d.fillOval(305, 485 + y, 10, 20);
                d.fillOval(335, 485 + y, 10, 20);
                d.setColor(new Color(106, 142, 174));
                d.fillOval(300, 350 + y, 50, 90);
                d.fillRectangle(300, 400 + y, 50, 90);
                d.setColor(new Color(114, 8, 10));
                d.fillCircle(325, 400 + y, 20);
                d.setColor(new Color(205, 218, 253));
                d.fillCircle(325, 400 + y, 15);
                d.setColor(new Color(255, 178, 15));
                d.drawLine(310, 508 + y, 310, 515 + y);
                d.drawLine(340, 508 + y, 340, 515 + y);
                y--;

            }

            @Override
            public void timePassed() {

            }
        };
        return backgroundSprite;
    }

    /**
     * returning list of the blocks.
     *
     * @return backgroundSprite
     */
    public List<Block> blocks() {
        int y;
        List<Block> blocksList = new ArrayList<>();
        Color[] colors = {new Color(26, 254, 73), new Color(131, 56, 236),
                new Color(48, 213, 86), new Color(92, 132, 111), new Color(114, 92, 123),
                new Color(136, 51, 136)};
        Random rand = new Random();
        for (int j = 0; j < 6; j++) {
            y = 100 + j * 30;
            for (int i = 0; i < 14; i++) {
                int color = rand.nextInt(6);
                Point point = new Point(724 - i * 54, y);
                Block block = new Block(point, 54, 30);
                block.setColor(colors[color]);
                blocksList.add(block);
            }
        }

        return blocksList;
    }

    /**
     * returning the number of blocks we need to remove.
     *
     * @return (int)
     */
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}

