package GameObjects;
import Geometric.Point;
/**
 * CollisionInfo Class.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * constructor.
     *
     * @param collisionPoint  (Point).
     * @param collisionObject (Collidable).
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * the function returns the point at which the collision occurs.
     *
     * @return collisionPoint (Point).
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the function returns the collidable object involved in the collision.
     *
     * @return collisionObject (Collidable).
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}