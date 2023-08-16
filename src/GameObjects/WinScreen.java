package GameObjects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Win screen.
 */
public class WinScreen implements Animation {

    private final KeyboardSensor keyboard;
    private final int score;
    private AnimationRunner runner;
    private boolean stop;

    /**
     * Instantiates a new Win screen.
     *
     * @param score    the score
     * @param runner   the runner
     * @param keyboard the keyboard
     */
    public WinScreen(int score, AnimationRunner runner, KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.runner = runner;
        this.score = score;
    }

    /**
     * draw the animation.
     *
     * @param d DrawSurface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score, 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.runner.getGui().close();
        }
    }

    /**
     * returning boolean that indicates if the animation to should stop.
     *
     * @return boolean
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
