package GameObjects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * class KeyPressStoppableAnimation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private final Animation animation;
    private final String key;
    private boolean isPressed;
    /**
     * constructor.
     *
     * @param sensor KeyboardSensor
     * @param key String
     * @param animation Animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        isPressed = true;
    }

    /**
     * draw the animation.
     *
     * @param d DrawSurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(this.key)) {
            isPressed = false;
        }

    }

    @Override
    public boolean shouldStop() {
        return !isPressed;
    }

}
