package GameObjects;

import Constants.Constants;
import Game.GameLevel;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {

    private final AnimationRunner ar;
    private final biuoop.KeyboardSensor keyboardSensor;
    private final GUI gui;
    private final Counter score;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar  the ar
     * @param ks  the ks
     * @param gui the gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.keyboardSensor = ks;
        this.ar = ar;
        this.gui = gui;
        this.score = new Counter(0);
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {


        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.ar, this.gui, this.score);
            level.initialize();

            while (level.getBlockcounter().getValue() > 0 && level.getBallsCounter().getValue() > 0) {
                level.run();
            }
            if (level.getBallsCounter().getValue() == 0) {
                break;
            }

        }
        this.ar.run(new KeyPressStoppableAnimation(this.keyboardSensor, this.keyboardSensor.SPACE_KEY,
                new WinScreen(this.score.getValue(), this.ar, this.gui.getKeyboardSensor())));


    }
}
