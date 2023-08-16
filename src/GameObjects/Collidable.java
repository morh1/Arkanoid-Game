/**
 * @author Mor Hanania
 * id:315112870
 */
package GameObjects;
import Geometric.Point;
import Geometric.Rectangle;

/**
 * The interface Collidable.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return (Rectangle) collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter          (Ball)
     * @param collisionPoint  (Point)
     * @param currentVelocity (Velocity)
     * @return (Velocity) velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
