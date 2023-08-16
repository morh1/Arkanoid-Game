package Geometric;
import Constants.Constants;
/**
 * @author Mor Hanania
 * id:315112870
 */

public class Point {
    private final double x;
    private final double y;
    /**
     * constructor.
     * @param x (double).
     * @param y (double).
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * the function calculate the distance of this point to the other point.
     *
     * @param other (Point).
     * @return double.
     */
    // distance -- return the
    public double distance(Point other) {
        return Math.sqrt((this.x - other.getX()) * (this.x - other.getX())
                + (this.y - other.getY()) * (this.y - other.getY()));
    }

    /**
     * the function return true is the points are equal, false otherwise.
     * @param other (Point).
     * @return boolean.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return Math.abs(this.x - other.getX())
                < Constants.EPSILON && Math.abs(this.y - other.getY()) < Constants.EPSILON;
    }

    /**
     * the function return the x value of the point.
     *
     * @return double.
     */
    public double getX() {
        return this.x;
    }

    /**
     * the function return the y value of the point.
     *
     * @return double.
     */
    public double getY() {
        return this.y;
    }
}
