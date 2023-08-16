package GameObjects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;


/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private final KeyboardSensor keyboard;
    private final int score;
    private AnimationRunner runner;
    private boolean stop;

    /**
     * Instantiates a new End screen.
     *
     * @param score    the score
     * @param runner   the runner
     * @param keyboard the keyboard
     */
    public EndScreen(int score, AnimationRunner runner, KeyboardSensor keyboard) {
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
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score, 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.runner.getGui().close();
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

