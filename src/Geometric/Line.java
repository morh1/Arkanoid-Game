package Geometric;
import Constants.Constants;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Mor Hanania
 * id:315112870
 */
public class Line {
    private final Point start;
    private final Point end;
    private final double x1;
    private final double x2;
    private final double y1;
    private final double y2;

    /**
     * constructor.
     *
     * @param start (Point).
     * @param end   (Point).
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.x1 = start.getX();
        this.x2 = end.getX();
        this.y1 = start.getY();
        this.y2 = end().getY();
    }

    /**
     * constructor.
     *
     * @param x1 (double).
     * @param y1 (double).
     * @param x2 (double).
     * @param y2 (double).
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * the function return the length of the line.
     *
     * @return double.
     */
    public double length() {
        return start().distance(end);
    }

    /**
     * the function Returns the middle point of the line.
     *
     * @return point.
     */
    public Point middle() {
        Point middle = new Point((x1 + x2) / 2, (y1 + y2) / 2);
        return middle;
    }

    /**
     * the function return the start point of the line.
     *
     * @return point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * the function return the end point of the line.
     *
     * @return point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * the function return true if the lines intersect, false otherwise.
     *
     * @param other (Line)
     * @return boolean.
     */
    public boolean isIntersecting(Line other) {
        double otherMaxY = Math.max(other.start().getY(), other.end().getY());
        double thisMaxY = Math.max(this.y1, this.y2);
        double otherMinY = Math.min(other.start().getY(), other.end().getY());
        double thisMinY = Math.min(this.y1, this.y2);
        double otherMaxX = Math.max(other.start().getX(), other.end().getX());
        double thisMaxX = Math.max(this.x1, this.x2);
        double otherMinX = Math.min(other.start().getX(), other.end().getX());
        double thisMinX = Math.min(this.x1, this.x2);
        double m1, m2, b1, b2;
        //they both vertical
        if ((this.x1 == this.x2) && (other.start().getX() == other.end().getX())) {
            //they haven't the same equasion.
            if (this.x1 != other.start().getX()) {
                return false;
            }
            //the intersection point is the start or the end of them
            return otherMaxY == thisMinY || otherMinY == thisMaxY;
            //they contain each other
        }
        //calculate the equasion parameters
        m1 = (this.y1 - this.y2) / (this.x1 - this.x2);
        m2 = (other.start().getY() - other.end().getY()) / (other.start().getX() - other.end().getX());
        b1 = -m1 * this.x1 + this.y1;
        b2 = -m2 * other.start().getX() + other.start().getY();
        //if they have the same equasion
        if (b1 == b2 && m1 == m2) {
            //they Strangers lines
            return !(thisMaxX < otherMinX) && !(otherMaxX < thisMinX);
        }
        return intersectionWith(other) != null;
    }

    /**
     * the function  Returns the intersection point if the lines intersect,
     * // and null otherwise.
     *
     * @param other (Line)
     * @return point.
     */
    public Point intersectionWith(Line other) {
        double m1, m2, b1, b2, potentialXPoint, potentialYPoint;
        double otherMaxY = Math.max(other.start().getY(), other.end().getY());
        double thisMaxY = Math.max(this.y1, this.y2);
        double otherMinY = Math.min(other.start().getY(), other.end().getY());
        double thisMinY = Math.min(this.y1, this.y2);
        double otherMaxX = Math.max(other.start().getX(), other.end().getX());
        double thisMaxX = Math.max(this.x1, this.x2);
        double otherMinX = Math.min(other.start().getX(), other.end().getX());
        double thisMinX = Math.min(this.x1, this.x2);

        //in case we got two vertical line
        if (this.x1 == this.x2 && other.start().getX() == other.end().getX()) {
            //the same vertical
            if (this.x1 == other.start().getX()) {
                //they continue each other
                if (otherMaxY == thisMinY) {
                    return new Point(this.x1, thisMinY);
                }
                if (otherMinY == thisMaxY) {
                    return new Point(this.x1, thisMaxY);
                }
            }
            return null;
        }
        //calculate the Inclines
        m1 = (this.y1 - this.y2) / (this.x1 - this.x2);
        m2 = (other.start().getY() - other.end().getY()) / (other.start().getX() - other.end().getX());
        //only this line is vertical
        if (this.x1 == this.x2) {
            potentialXPoint = this.x1;
            b2 = -m2 * other.start().getX() + other.start().getY();
            potentialYPoint = m2 * this.x1 + b2;
        } else if (other.start().getX() == other.end().getX()) { //only the other line is vertical
            potentialXPoint = other.start().getX();
            b1 = -m1 * this.x1 + this.y1;
            potentialYPoint = m1 * potentialXPoint + b1;
        } else { //both anvertical
            //find the equation's parameters
            b1 = -m1 * this.x1 + this.y1;
            b2 = -m2 * other.start().getX() + other.start().getY();
            //if the inclines are equals
            if (m1 == m2) {
                //the lines Parallel
                if (b1 != b2) {
                    return null;
                }
                //the same equation, if they continue each other from thr same point.
                if (otherMaxX == thisMinX) {
                    //get the y of the otherMaxX point
                    if (otherMaxX == other.start().getX()) {
                        return new Point(otherMaxX, other.start().getY());
                    }
                    return new Point(otherMaxX, other.end().getY());
                }
                if (otherMinX == thisMaxX) {
                    //get the y of the otherMinX
                    if (otherMinX == other.start().getX()) {
                        return new Point(otherMinX, other.start().getY());
                    }
                    return new Point(otherMinX, other.end().getY());
                }
                return null;
            }
            potentialXPoint = (b2 - b1) / (m1 - m2);
            potentialYPoint = m1 * potentialXPoint + b1;

        }
        Point potentialPoint = new Point(potentialXPoint, potentialYPoint);
        //if potential x point in the same range of the 2 lines.
        if (pointInLine(potentialPoint, other) && (pointInLine(potentialPoint, this))) {
            return potentialPoint;
        }
        return null;

    }

    private boolean pointInLine(Point point, Line line) {
        double maxY = Math.max(line.end.getY(), line.start.getY());
        double minY = Math.min(line.end.getY(), line.start.getY());
        double maxX = Math.max(line.end.getX(), line.start.getX());
        double minX = Math.min(line.end.getX(), line.start.getX());
        return ((maxX >= point.getX() && minX <= point.getX())
                || (Math.abs(point.getX() - line.start.getX()) <= Constants.EPSILON)
                || (Math.abs(point.getX() - line.end.getX()) <= Constants.EPSILON))
                && ((maxY >= point.getY() && minY <= point.getY())
                || (Math.abs(point.getY() - line.start.getY()) <= Constants.EPSILON)
                || (Math.abs(point.getY() - line.end.getY()) <= Constants.EPSILON));
    }

    /**
     * the function return true is the lines are equal, false otherwise.
     *
     * @param other (Line).
     * @return boolean.
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start()) && this.end.equals(other.end()))
                || (this.start.equals(other.end()) && this.end.equals(other.start())));
    }

    /**
     * the function return the intersection points with given rect.
     *
     * @param rect (Rectangle).
     * @return (List).
     */
    public List<Point> intersectionWithRec(Rectangle rect) {
        List<Point> pointList = new ArrayList<Point>();
        for (int i = 0; i < rect.getRectRib().length; i++) {
            if (this.intersectionWith(rect.getRectRib()[i]) != null) {
                pointList.add(this.intersectionWith(rect.getRectRib()[i]));

            }
        }

        return pointList;
    }

    /**
     * the function return the closes' intersection point with the given rect.
     *
     * @param rect (Rectangle).
     * @return (List).
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point closestPoint = null;
        double minDistance = this.start.distance(this.end);
        List<Point> intersectionPointList = new ArrayList<Point>();
        intersectionPointList = this.intersectionWithRec(rect);
        for (int i = 0; i < intersectionPointList.size(); i++) {
            if (this.start.distance(intersectionPointList.get(i)) < minDistance) {
                closestPoint = intersectionPointList.get(i);
                minDistance = this.start.distance(intersectionPointList.get(i));
            }
        }
        return closestPoint;
    }


}
