/**
 * @author Mor Hanania
 * id:315112870
 */
package GameObjects;
import biuoop.DrawSurface;
/**
 *Sprite interface.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d (DrawSurface).
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}