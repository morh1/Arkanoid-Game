package GameObjects;

import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private int countFrom;
    private final SpriteCollection gameScreen;
    private final Sleeper sleeper;
    private boolean stop;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        this.sleeper = new Sleeper();
    }
    /**
     * draw the animation.
     *
     * @param d DrawSurface
     */
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(new Color(255, 0, 150));
        d.drawText(400, 300, Integer.toString(countFrom), 100);
        countFrom--;
        if (countFrom == 0) {
            this.stop = true;
        }
    }
    /**
     * returning boolean that indicates if the animation to should stop.
     * @return boolean
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
