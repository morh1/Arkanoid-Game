/**
 * @author Mor Hanania
 * id:315112870
 */
package GameObjects;

import Geometric.Point;
import Geometric.Rectangle;
import biuoop.DrawSurface;
import Game.GameLevel;
import Constants.Constants;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //the shape of the block.
    private final Rectangle rectangle;
    private final Point upperLeft;
    private final int width;
    private final int height;
    //private java.awt.Color color = new Color(100, 17, 173);
    private java.awt.Color color = new Color(45, 43, 66);
    private final List<HitListener> hitListeners = new ArrayList<>();

    /**
     * constructor.
     *
     * @param upperLeftP (Point).
     * @param width      (int).
     * @param height     (int).
     */
    public Block(Point upperLeftP, int width, int height) {
        this.rectangle = new Rectangle(upperLeftP, width, height);
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeftP;
    }

    /**
     * constructor.
     *
     * @param r (Rectangle).
     */
    public Block(Rectangle r) {
        this.width = r.getWidth();
        this.height = r.getHeight();
        this.upperLeft = r.getUpperLeft();
        this.rectangle = r;
    }

    /**
     * constructor.
     *
     * @param x      (double).
     * @param y      (double).
     * @param width  (int).
     * @param height (int).
     */
    public Block(double x, double y, int width, int height) {
        this.upperLeft = new Point(x, y);
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.width = width;
        this.height = height;
    }

    /**
     * the function gets the shape of the block.
     *
     * @return rectangle (Rectangle)
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * the function adds the block to the Collidable list and to the Sprite list.
     *
     * @param gameLevel (Game)
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.getEnvironment().addCollidable(this);
        gameLevel.getSprites().addSprite(this);
    }

    /**
     * the function removes the block from the Collidable list and the Sprite list.
     *
     * @param gameLevel (Game)
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.getEnvironment().removeCollidable(this);
        gameLevel.getSprites().removeSprite(this);
    }

    /**
     * the function gets the block's width.
     *
     * @return width (int)
     */
    public int getWidth() {
        return width;
    }


    /**
     * the function gets the block's height.
     *
     * @return height (int)
     */
    public int getHeight() {
        return height;
    }

    /**
     * the function gets the block's upperLeft point.
     *
     * @return upperLeft (Point)
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * the function gets rectangle.
     *
     * @return rectangle (Rectangle)
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * the function draw the ball and fill it.
     *
     * @param surface (DrawSurface).
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(),
                this.getWidth(), this.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(),
                this.getWidth(), this.getHeight());

    }

    @Override
    public void timePassed() {

    }

    /**
     * the function update the velocity direction according to the collision Point.
     *
     * @param collisionPoint  (Point).
     * @param currentVelocity (Velocity).
     * @return (Velocity)
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        if (Math.abs(collisionPoint.getY() - (this.getCollisionRectangle().getUpperLeft().getY() + this.height))
                <= Constants.EPSILON || (Math.abs(collisionPoint.getY()
                - (this.getCollisionRectangle().getUpperLeft().getY())) <= Constants.EPSILON)) {
            dy *= -1;
        }
        //collision occur with the vertical ribs of the block.
        if ((Math.abs(collisionPoint.getX() - (this.getCollisionRectangle().getUpperLeft().getX() + this.width))
                <= Constants.EPSILON) || (Math.abs(collisionPoint.getX()
                - this.getCollisionRectangle().getUpperLeft().getX()) <= Constants.EPSILON)) {
            dx *= -1;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * the function sets the color parameter.
     *
     * @param color (Color).
     */
    public void setColor(Color color) {
        this.color = color;
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
