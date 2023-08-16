package GameObjects;

import Constants.Constants;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;


/**
 * The type Animation runner.
 */
public class AnimationRunner {
    private final GUI gui;
    private int framesPerSecond;
    private final Sleeper sleeper;

    /**
     * Instantiates a new Animation runner.
     *
     * @param gui the gui
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        setFramesPerSecond(Constants.MILI_SEC);
        this.sleeper = new Sleeper();
    }

    /**
     * Sets frames per second.
     *
     * @param framesPerSecond the frames per second
     */
    public void setFramesPerSecond(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * Gets frames per second.
     *
     * @return the frames per second
     */
    public int getFramesPerSecond() {
        return framesPerSecond;
    }

    /**
     * Gets gui.
     *
     * @return the gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Run.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = getFramesPerSecond();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}


