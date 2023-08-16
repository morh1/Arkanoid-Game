/**
 * @author Mor Hanania
 * id:315112870
 */
package GameObjects;

import Geometric.Rectangle;
import Geometric.Line;
import Geometric.Point;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Constants.Constants;
import Game.GameLevel;

/**
 * Ball class.
 */
public class Ball implements Sprite, HitNotifier {
    //constants
    private static final int DEFAULT_VALUE = 1;
    private Point point;
    private final int radius;
    private double x;
    private double y;
    //initialize the Velocity with default value.
    private Velocity ballV = new Velocity(DEFAULT_VALUE, DEFAULT_VALUE);
    private java.awt.Color color;
    private GameEnvironment gameEnvironment;
    private final List<HitListener> hitListeners = new ArrayList<>();

    /**
     * constructor.
     *
     * @param x     (double).
     * @param y     (double).
     * @param r     (int).
     * @param color (java.awt.Color).
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.x = x;
        this.y = y;
        this.point = new Point(x, y);
        this.color = color;
        this.radius = r;
        this.ballV = this.ballV.fromAngleAndSpeed(0, Constants.BALL_SPEED);
    }

    /**
     * the function sets the ball's by angel and speed.
     *
     * @param angel (double)
     * @param speed (double).
     */
    public void setVFromAngleAndSpeed(double angel, double speed) {
        this.ballV = ballV.fromAngleAndSpeed(angel, speed);
    }

    /**
     * the function randomize a color.
     */
    private void randColor() {
        Random rand = new Random();
        float red = rand.nextFloat();
        float green = rand.nextFloat();
        float blue = rand.nextFloat();
        this.color = new Color(red, green, blue);
    }

    /**
     * the function returns the x value of the center point of the ball.
     *
     * @return double. x
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * the function returns the y value of the center point of the ball.
     *
     * @return double. y
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * the function returns the radius value of the ball.
     *
     * @return int. size
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * the function returns the color of the ball.
     *
     * @return int. color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * the function draw the ball.
     *
     * @param surface (DrawSurface).
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(new Color(255, 0, 150));
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * the function responsible for ball's movement.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * the function set the velocity of the ball.
     *
     * @param v (Velocity).
     */
    public void setVelocity(Velocity v) {
        this.ballV = v;
    }

    /**
     * the function sets the velocity of the ball.
     *
     * @param dx (double).
     * @param dy (double).
     */
    public void setVelocity(double dx, double dy) {
        this.ballV = new Velocity(dx, dy);
    }

    /**
     * the function returns the velocity.
     *
     * @return ballV(Velocity). velocity
     */
    public Velocity getVelocity() {
        return this.ballV;
    }

    /**
     * the function sets the ball's velocity.
     *
     * @param v (Velocity)
     */
    public void setVelocityByV(Velocity v) {
        this.ballV = v;
    }

    /**
     * the function returns the ball's center point.
     *
     * @return (Point) point
     */
    public Point getPoint() {
        return this.point;
    }

    /**
     * the function calculate the ball's path line.
     *
     * @return (Line) line
     */
    public Line ballTrajectory() {
        Point start = this.getPoint();
        Point end = new Point(this.getX() + this.ballV.getDx(), this.getY() + this.ballV.getDy());
        return new Line(this.getPoint(), this.ballV.applyToPoint(this.getPoint()));
    }

    /**
     * the function handle with the case when the ball hits the corner of the blocks.
     *
     * @param rect      (Rectangle)
     * @param collision (Point)
     * @return (boolean)
     */
    private boolean inCorner(Rectangle rect, Point collision) {
        double dx = this.getVelocity().getDx(), dy = this.getVelocity().getDy();
        Point topLeft = rect.getUpperLeft();
        Point topRight = rect.getUpperLeft();
        Point bottomLeft = rect.getUpperLeft();
        Point bottomRight = rect.getUpperLeft();
        //check in every corner
        if (collision.equals(topLeft)) {
            this.setPoint(collision.getX() - 1, collision.getY() - 1);
            this.setVelocity(-dx, -dy);
            return true;
        } else if (collision.equals(topRight)) {
            this.setPoint(collision.getX() + 1, collision.getY() - 1);
            this.setVelocity(-dx, -dy);
            return true;
        } else if (collision.equals(bottomLeft)) {
            this.setPoint(collision.getX() - 1, collision.getY() + 1);
            this.setVelocity(-dx, -dy);
            return true;
        } else if (collision.equals(bottomRight)) {
            this.setPoint(collision.getX() + 1, collision.getY() + 1);
            this.setVelocity(-dx, -dy);
            return true;
        }
        return false;
    }

    /**
     * the function check if the ball isn't Collides with the blocks and change the velocity accordance.
     */
    public void moveOneStep() {
        Line ballTrajectory = this.ballTrajectory();
        CollisionInfo collidableInfo = gameEnvironment.getClosestCollision(ballTrajectory);
        //if there is a collision
        if (collidableInfo != null) {
            Rectangle collidRect = collidableInfo.collisionObject().getCollisionRectangle();
            Point collidRectPoint = collidRect.getUpperLeft();
            //if the ball doesn't hit the corner of the block
            if (!(inCorner(collidRect, collidableInfo.collisionPoint()))) {
                //check in witch way the collision accrue and change the ball position and velocity.
                this.setVelocityByV(collidableInfo.collisionObject().hit(this, collidableInfo.collisionPoint(),
                        this.getVelocity()));
                if (Math.abs(collidableInfo.collisionPoint().getY() - (collidRectPoint.getY() + collidRect.getHeight()))
                        <= Constants.EPSILON) {
                    this.setPoint(collidableInfo.collisionPoint().getX(), collidableInfo.collisionPoint().getY() + 1);
                } else if (Math.abs(collidableInfo.collisionPoint().getY() - collidRectPoint.getY())
                        <= Constants.EPSILON) {
                    this.setPoint(collidableInfo.collisionPoint().getX(), collidableInfo.collisionPoint().getY() - 1);
                }
                if (Math.abs(collidableInfo.collisionPoint().getX() - collidRectPoint.getX() + collidRect.getWidth())
                        <= Constants.EPSILON) {
                    this.setPoint(collidableInfo.collisionPoint().getX() + 1, collidableInfo.collisionPoint().getY());
                } else if (Math.abs(collidableInfo.collisionPoint().getX() - collidRectPoint.getX())
                        <= Constants.EPSILON) {
                    this.setPoint(collidableInfo.collisionPoint().getX() - 1, collidableInfo.collisionPoint().getY());
                }
            }

        } else {
            this.point = this.getVelocity().applyToPoint(this.point);
        }
    }


    /**
     * the function sets the center point of the ball.
     *
     * @param x (double)
     * @param y (double)
     */
    public void setPoint(double x, double y) {
        this.x = x;
        this.y = y;
        this.point = new Point(x, y);
    }

    /**
     * the function adds the ball ti the sprites list.
     *
     * @param gameLevel (Game)
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.getSprites().addSprite(this);
    }

    /**
     * the function adds the ball ti the sprites list.
     *
     * @param gameEnvironment (GameEnvironment)
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Remove from game.
     *
     * @param g the g
     */
    public void removeFromGame(GameLevel g) {
        g.getSprites().removeSprite(this);
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl (HitListener)
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl (HitListener)
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}