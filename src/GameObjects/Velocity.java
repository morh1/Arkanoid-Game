/**
 * @author Mor Hanania
 * id:315112870
 */
package GameObjects;
import Geometric.Point;

/**
 * The type Velocity.
 */
public class Velocity {
    private double dx = 1, dy = 1;

    /**
     * constructor.
     *
     * @param dx (double).
     * @param dy (double).
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * the function returns the change of the velocity in the X.
     *
     * @return double. dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * the function returns the change of the velocity in the Y.
     *
     * @return double. dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * the function Take a point with position (x,y) and return a new point
     * // with position (x+dx, y+dy).
     *
     * @param p (Point).
     * @return newPoint(Point). point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * the function returns new velocity the created according to an angle and speed.
     *
     * @param angle (double)
     * @param speed (double).
     * @return (Velocity). velocity
     */
    public Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = angle % 360;
        angle = Math.toRadians(angle);
        double dx = speed * Math.sin(angle);
        double dy = -speed * Math.cos(angle);
        return new Velocity(dx, dy);
    }
}
