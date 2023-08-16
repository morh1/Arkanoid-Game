package GameObjects;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * SpriteCollection class.
 */
public class SpriteCollection {
    private final java.util.List<Sprite> spriteList;
    /**
     * constructor.
     */
    public SpriteCollection() {
        spriteList = new ArrayList<>();
    }
    /**
     * adds the given sprite to the sprite list.
     * @param s (Sprite).
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }
    /**
     * removes the given sprite from the sprite list.
     * @param s (Sprite).
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }
    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteList = new ArrayList<Sprite>(this.spriteList);
        for (Sprite sprite : spriteList) {
            sprite.timePassed();
        }
    }
    /**
     * call drawOn(d) on all sprites.
     * @param d (DrawSurface)
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteList) {
            sprite.drawOn(d);
        }
    }
}