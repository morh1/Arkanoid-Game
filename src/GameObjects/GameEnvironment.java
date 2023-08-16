/**
 * @author Mor Hanania
 * id:315112870
 */

package GameObjects;

import Geometric.Point;
import Geometric.Line;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * GameEnvironment class.
 */
public class GameEnvironment {
    private final java.util.List<Collidable> collidList;
    private final GUI gui;

    /**
     * constructor.
     *
     * @param g (Gui)
     */
    public GameEnvironment(GUI g) {
        collidList = new ArrayList<>();
        this.gui = g;
    }

    /**
     * the function returns the collidable list.
     *
     * @return (List < Collidable >).
     */
    public List<Collidable> getCollidList() {
        return collidList;
    }

    /**
     * the function add the given collidable to the environment.
     *
     * @param c (Collidable).
     */
    public void addCollidable(Collidable c) {
        this.collidList.add(c);
    }

    /**
     * the function removes the given collidable from the environment.
     *
     * @param c (Collidable).
     */
    public void removeCollidable(Collidable c) {
        this.collidList.remove(c);
    }

    /**
     * the function returns the gui.
     *
     * @return gui (GUI).
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory (Line).
     * @return (CollisionInfo)
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closesPoint = null;
        Collidable closestCollidable = null;
        List<Collidable> collidList = new ArrayList<Collidable>(this.collidList);
        //check Intersection with all the collidable objects
        for (Collidable collideable : collidList) {
            if (trajectory.closestIntersectionToStartOfLine(collideable.getCollisionRectangle()) != null) {
                if (closesPoint == null || trajectory.start().distance(closesPoint)
                        < trajectory.start().
                        distance(trajectory.closestIntersectionToStartOfLine(collideable.getCollisionRectangle()))) {
                    //update the closes point and the closes collidable object.
                    closesPoint = trajectory.closestIntersectionToStartOfLine(collideable.getCollisionRectangle());
                    closestCollidable = collideable;
                }
            }
        }
        if (closesPoint == null) {
            return null;
        }
        return new CollisionInfo(closesPoint, closestCollidable);
    }
}
