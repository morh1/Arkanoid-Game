package GameObjects;

import Geometric.Point;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level3 class.
 */
public class Level3 implements LevelInformation {
    private static final int NUM_OF_BALLS = 5;
    private static final double SPEED = 5;
    private static final int PADDLE_SPEED = 10;
    private static final int PADDLE_WIDTH = 120;
    private static final String LEVEL_NAME = "LEVEL-3";

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
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            vlist.add(velocity.fromAngleAndSpeed(100 - i * 55, SPEED));
        }
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
             private int x, y, factor = 1;

            @Override
            public void drawOn(DrawSurface d) {
                for (int i = 0; i < 60; i++) {
                    d.setColor(new Color(105 + i / 2, 48 + i * 3, 195 + i));
                    d.fillRectangle(0, 600 - i * 10, 800, 10);
                }
                d.fillOval(200 + x, 300, 40, 20);
                d.fillOval(160 + x, 350, 40, 20);
                d.fillOval(280 + x, 325, 40, 20);
                d.fillOval(122 + x, 280, 40, 20);
                d.fillOval(250 + x, 260, 40, 20);
                for (int i = 0; i < 20; i++) {
                    d.drawLine(201 + x, 306, 195 + x, 298 + i);
                    d.drawLine(161 + x, 356, 155 + x, 348 + i);
                    d.drawLine(281 + x, 331, 275 + x, 323 + i);
                    d.drawLine(123 + x, 286, 117 + x, 278 + i);
                    d.drawLine(251 + x, 266, 245 + x, 258 + i);
                }
                x++;
                d.setColor(new Color(174, 38, 13));
                d.fillOval(50, 500, 65, 40);
                for (int i = 0; i < 40; i++) {
                    d.drawLine(55, 520, 35, 500 + i);
                }
                d.fillCircle(82, 500, 5);
                d.fillCircle(89, 502, 4);
                d.fillCircle(75, 502, 4);
                d.setColor(new Color(220, 47, 2));
                d.fillCircle(115, 520, 2);
                //the eyes
                d.setColor(Color.black);
                d.fillCircle(233 + x, 308, 2);
                d.fillCircle(313 + x, 333, 2);
                d.fillCircle(282 + x, 267, 2);
                d.fillCircle(157 + x, 287, 2);
                d.fillCircle(195 + x, 358, 2);
                d.setColor(new Color(255, 0, 110));
                d.fillOval(650, 250, 65, 40);
                for (int i = 0; i < 30; i++) {
                    d.drawLine(700, 272, 735, 250 + i);
                }
                d.fillCircle(680, 250, 5);
                d.fillCircle(672, 252, 4);
                d.fillCircle(688, 252, 4);
                d.setColor(Color.white);
                d.fillCircle(100, 512, 4);
                d.fillCircle(660, 265, 4);
                d.setColor(Color.black);
                d.fillCircle(102, 512, 3);
                d.fillCircle(658, 265, 3);
                d.drawLine(300, -200 + y, 300, 0 + y);
                d.drawLine(300, 0 + y, 295, -3 + y);
                d.drawLine(300, 0 + y, 305, -3 + y);
                d.drawLine(550, -500 + y, 550, 0 + y);
                d.drawLine(550, 0 + y, 545, -3 + y);
                d.drawLine(550, 0 + y, 555, -3 + y);
                y = y + factor * 3;
                if (y == 0 || y >= 200) {
                    factor = -1 * factor;
                }
                //the mouse
                d.setColor(new Color(220, 47, 2));
                d.fillCircle(115, 520, 2);
                d.fillCircle(653, 273, 2);
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
        int x, y;
        List<Block> blocksList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            y = 100 + i * 30;
            x = 780;
            for (int j = 0; j < 12 - i; j++) {
                x -= 50;
                Point point = new Point(x, y);
                Block block = new Block(point, 50, 30);
                java.awt.Color color = new Color(55 * i / 2 + 50, 20, 150);
                block.setColor(color);
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

