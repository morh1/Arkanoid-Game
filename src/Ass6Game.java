/**
 * @author Mor Hanania
 * id:315112870
 */

import GameObjects.LevelInformation;
import GameObjects.Level1;
import GameObjects.Level2;
import GameObjects.Level3;
import GameObjects.Level4;
import GameObjects.AnimationRunner;
import GameObjects.GameFlow;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 3 game.
 */
public class Ass6Game {
    /**
     * The main function creating the game.
     *
     * @param args the Strings array.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Game", 800, 600);
        LevelInformation level1 = new Level1();
        LevelInformation level2 = new Level2();
        LevelInformation level3 = new Level3();
        LevelInformation level4 = new Level4();
        List<LevelInformation> levels = new ArrayList<>();
        for (String arg : args) {
            if (arg.equals("1")) {
                levels.add(level1);
            }
            if (arg.equals("2")) {
                levels.add(level2);

            }
            if (arg.equals("3")) {
                levels.add(level3);
            }
            if (arg.equals("4")) {
                levels.add(level4);
            }
        }
        if (levels.size() == 0) {
            levels.add(level1);
            levels.add(level2);
            levels.add(level3);
            levels.add(level4);
        }
        AnimationRunner runner = new AnimationRunner(gui);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(runner, keyboard, gui);
        gameFlow.runLevels(levels);
    }
}
