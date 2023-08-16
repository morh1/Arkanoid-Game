package GameObjects;
import Game.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Level name.
 */

public class LevelName implements Sprite {
    private final String str;

    /**
     * constructor.
     *
     * @param str the string
     */
    public LevelName(String str) {
        this.str = str;
    }

    private String getStr() {
        return this.str;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d (DrawSurface).
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(450, 15, "Level Name: " + getStr(), 15);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        // this.score=ScoreTrack.getCurrentScore();
    }

    /**
     * the function adds the block to the Collidable list and to the Sprite list.
     *
     * @param gameLevel (Game)
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.getSprites().addSprite(this);
    }

}

