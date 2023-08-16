package GameObjects;

import Game.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    private Counter getScore() {
        return this.score;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d (DrawSurface).
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(250, 15, "Score: " + getScore().getValue(), 15);
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
