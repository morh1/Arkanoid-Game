package Geometric;
/**
 * @author Mor Hanania
 * id:315112870
 */
public class Rectangle {
    private Point upperLeft;
    private int width, height;
    private final double size = width * height;

    /**
     * the function returns the rectangle's size.
     *
     * @return size (double).
     */
    public double getSize() {
        return size;
    }

    /**
     * contracture Create a new rectangle with location and width/height.
     *
     * @param upperLeft (Point)
     * @param width     (int)
     * @param height    (int)
     */
    public Rectangle(Point upperLeft, int width, int height) {
        this.height = height;
        this.upperLeft = upperLeft;
        this.width = width;
    }

    /**
     * the function Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line (Line)
     * @return (List).
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        return line.intersectionWithRec(this);
    }

    /**
     * the function returns the rectangle's width.
     *
     * @return width (double).
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * the function returns the rectangle's height.
     *
     * @return height (double).
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * the function returns the rectangle's upper Left point.
     *
     * @return upperLeft (Point).
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * the function sets the rectangle's upper Left point.
     *
     * @param  upperLeft (Point).
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }

    /**
     * the function returns an array of the ribs of the rectangle.
     *
     * @return rectRib (Line[]).
     */

    public Line[] getRectRib() {
        //rectangle has 4 ribs
        Line[] rectRib = new Line[4];
        Line topLine, bottomLine, leftLine, rightLine;
        //creates the lines
        topLine = new Line(this.getUpperLeft().getX(), this.getUpperLeft().getY(),
                this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
        rectRib[0] = topLine;
        bottomLine = new Line(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight(),
                this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY() + this.getHeight());
        rectRib[1] = bottomLine;
        leftLine = new Line(this.getUpperLeft().getX(), this.getUpperLeft().getY(),
                this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
        rectRib[2] = leftLine;
        rightLine = new Line(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY(),
                this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY() + this.getHeight());
        rectRib[3] = rightLine;
        return rectRib;
    }
}