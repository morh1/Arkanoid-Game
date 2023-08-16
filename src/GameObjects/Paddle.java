/**
 * @author Mor Hanania
 * id:315112870
 */
package GameObjects;

import Geometric.Point;
import Geometric.Line;
import Geometric.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import Constants.Constants;
import Game.GameLevel;

import java.awt.Color;


/**
 * Paddle class.
 */
public class Paddle implements Collidable, Sprite {
    private Block block;
    private final java.awt.Color color = new Color(45, 43, 66);
    private final GUI gui;
    private final Line rightLimit;
    private final Line leftLimit;
    private final Ball[] balls;
    private final int paddleMovement;
    private final int paddleWidth;
    private static final int GET_PADDLE_HEIGHT = 20;

    /**
     * constructor.
     *
     * @param gui        (GUI).
     * @param rightLimit (Line).
     * @param leftLimit  (Line).
     * @param balls      (Balls[]).
     * @param speed
     * @param paddleWidth
     */
    public Paddle(GUI gui, Line rightLimit, Line leftLimit, Ball[] balls, int speed, int paddleWidth) {
        block = new Block(Constants.PADDLE_POINT, paddleWidth, GET_PADDLE_HEIGHT);
        this.gui = gui;
        this.rightLimit = rightLimit;
        this.leftLimit = leftLimit;
        this.balls = balls;
        this.paddleMovement = speed;
        this.paddleWidth = paddleWidth;
    }

    /**
     * the function returns the leftLimit.
     *
     * @return leftLimit (Line).
     */
    public Line getLeftLimit() {
        return leftLimit;
    }

    /**
     * the function returns the rightLimit.
     *
     * @return rightLimit (Line).
     */
    public Line getRightLimit() {
        return rightLimit;
    }

    /**
     * the function returns the paddle shape.
     *
     * @return rectangle (Rectangle).
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block.getRectangle();
    }

    @Override
    public Velocity hit(Ball b, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        //
        double[] paddleDivision = new double[6];
        //creating six division points.
        for (int i = 0; i < paddleDivision.length; i++) {
            paddleDivision[i] = this.block.getRectangle().getUpperLeft().getX() + i * this.paddleWidth / 5;
        }
        double newSpeed = Math.sqrt(dx * dx + dy * dy);
        //each division on the paddle get its own angel.
        if ((collisionPoint.getX() > paddleDivision[0] && collisionPoint.getX() < paddleDivision[1])
                || collisionPoint.getX() == paddleDivision[0] || collisionPoint.getX() == paddleDivision[1]) {
            return currentVelocity.fromAngleAndSpeed(300, newSpeed);
        } else if ((collisionPoint.getX() > paddleDivision[1] && collisionPoint.getX() < paddleDivision[2])
                || collisionPoint.getX() == paddleDivision[2]) {
            return currentVelocity.fromAngleAndSpeed(330, newSpeed);
        } else if ((collisionPoint.getX() > paddleDivision[2] && collisionPoint.getX() < paddleDivision[2])
                || collisionPoint.getX() == paddleDivision[3]) {
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        } else if ((collisionPoint.getX() > paddleDivision[3] && collisionPoint.getX() < paddleDivision[3])
                || collisionPoint.getX() == paddleDivision[4]) {
            return currentVelocity.fromAngleAndSpeed(30, newSpeed);
        }
        return currentVelocity.fromAngleAndSpeed(60, newSpeed);
    }

    /**
     * handale with the case when the paddle moves on the ball.
     */
    public void inPaddleZoon() {

        for (Ball balls : balls) {
            Point paddlePoint = this.block.getRectangle().getUpperLeft();
            double dx = balls.getVelocity().getDx();
            double dy = balls.getVelocity().getDy();
            //check if the ball inside the paddle in the left middle and emit it.
            if (balls.getX() >= paddlePoint.getX() && balls.getX() <= (paddlePoint.getX() + this.paddleWidth / 2)
                    && balls.getY() > paddlePoint.getY()
                    && balls.getY() <= paddlePoint.getY() + Constants.PADDLE_HEIGHT) {
                if (balls.getX() - this.paddleMovement <= this.leftLimit.start().getX()) {
                    balls.setPoint(this.leftLimit.start().getX() + 1, balls.getY());
                } else {
                    balls.setPoint(paddlePoint.getX() - this.paddleMovement, balls.getY());
                }
                balls.setVelocity(-dx, dy);
                //check if the ball inside the paddle in the right middle and emit it.
            } else if (balls.getX() >= paddlePoint.getX() + this.paddleWidth / 2 && balls.getX()
                    <= (paddlePoint.getX() + this.paddleWidth) && balls.getY() > paddlePoint.getY()
                    && balls.getY() <= paddlePoint.getY() + Constants.PADDLE_HEIGHT) {
                if (balls.getX() + this.paddleMovement >= this.rightLimit.start().getX()) {
                    balls.setPoint(this.rightLimit.start().getX() - 1, balls.getY());
                } else {
                    balls.setPoint(paddlePoint.getX() + this.paddleWidth + this.paddleMovement - 1,
                            balls.getY());
                }
                balls.setVelocity(-dx, dy);
            }
        }
    }

    /**
     * the function draw the paddle and fill it.
     *
     * @param d (DrawSurface).
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) block.getRectangle().getUpperLeft().getX(),
                (int) block.getRectangle().getUpperLeft().getY(), block.getRectangle().getWidth(),
                block.getRectangle().getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) block.getRectangle().getUpperLeft().getX(),
                (int) block.getRectangle().getUpperLeft().getY(),
                block.getRectangle().getWidth(), block.getRectangle().getHeight());
    }

    /**
     * the function responsible about the paddle movement by the keyboard.
     */
    @Override
    public void timePassed() {
        inPaddleZoon();
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        if (this.block.getRectangle().intersectionPoints(this.getLeftLimit()).size() == 0) {
            if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
                moveLeft();
            }
        }
        if (this.block.getRectangle().intersectionPoints(this.getRightLimit()).size() == 0) {
            if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
                moveRight();

            }
        }
    }

    /**
     * the function adds the block to the Collidable list and to the Sprite list.
     *
     * @param g (Game)
     */
    public void addToGame(GameLevel g) {
        g.getEnvironment().addCollidable(this);
        g.getSprites().addSprite(this);
    }

    /**
     * the function moves the paddle left.
     */
    public void moveLeft() {
        Point point = new Point(this.block.getRectangle().getUpperLeft().getX() - this.paddleMovement,
                this.block.getRectangle().getUpperLeft().getY());
        Rectangle rectangle = new Rectangle(point, this.getCollisionRectangle().getWidth(),
                this.getCollisionRectangle().getHeight());
        this.block = new Block(rectangle);
    }

    /**
     * the function moves the paddle right.
     */
    public void moveRight() {
        Point point = new Point(this.block.getRectangle().getUpperLeft().getX() + this.paddleMovement,
                this.block.getRectangle().getUpperLeft().getY());
        Rectangle rectangle = new Rectangle(point, this.getCollisionRectangle().getWidth(),
                this.getCollisionRectangle().getHeight());
        this.block = new Block(rectangle);
    }
}
