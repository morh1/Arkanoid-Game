/**
 * @author Mor Hanania
 * id:315112870
 */
package Game;
import biuoop.DrawSurface;
import biuoop.GUI;
import Geometric.Line;
import GameObjects.Counter;
import GameObjects.Animation;
import GameObjects.AnimationRunner;
import GameObjects.SpriteCollection;
import GameObjects.GameEnvironment;
import GameObjects.LevelInformation;
import GameObjects.Collidable;
import GameObjects.Sprite;
import java.awt.Color;
import GameObjects.BallRemover;
import GameObjects.ScoreIndicator;
import GameObjects.Block;
import Constants.Constants;
import biuoop.KeyboardSensor;
import GameObjects.LevelName;
import GameObjects.BlockRemover;
import GameObjects.ScoreTrackingListener;
import GameObjects.KeyPressStoppableAnimation;
import GameObjects.Ball;
import GameObjects.EndScreen;
import GameObjects.PauseScreen;
import GameObjects.CountdownAnimation;
import GameObjects.Paddle;


/**
 * Game class.
 */
public class GameLevel implements Animation {
    //creates the paddle limits
    private static final Line LEFT_LIMIT = new Line(Constants.BORDER_WIDTH, 0, Constants.BORDER_WIDTH,
            Constants.SCREEN_HEIGHT);
    private static final Line RIGHT_LIMIT = new Line(Constants.SCREEN_WIDTH - Constants.BORDER_WIDTH, 0,
            Constants.SCREEN_WIDTH - Constants.BORDER_WIDTH, Constants.SCREEN_HEIGHT);
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final GUI gui;
    private final Counter score;
    private final AnimationRunner runner;
    private final biuoop.KeyboardSensor keyboard;
    private final LevelInformation levelInfo;
    private Counter blockcounter;
    private Counter ballcounter;
    private boolean running;

    /**
     * constructor creates the frames blocks.
     *
     * @param level    (LevelInformation)
     * @param keyboard (KeyboardSensor)
     * @param ar       (AnimationRunner)
     * @param gui      (gui)
     * @param score    (Score)
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard, AnimationRunner ar, GUI gui, Counter score) {
        this.score = score;
        this.gui = gui;
        sprites = new SpriteCollection();
        environment = new GameEnvironment(gui);
        runner = ar;
        this.keyboard = keyboard;
        this.levelInfo = level;
    }

    /**
     * Gets blockcounter.
     *
     * @return the blockcounter
     */
    public Counter getBlockcounter() {
        return this.blockcounter;
    }

    /**
     * Gets balls counter.
     *
     * @return the balls counter
     */
    public Counter getBallsCounter() {
        return this.ballcounter;
    }

    /**
     * Gets counter.
     *
     * @return the counter
     */
    public Counter getCounter() {
        return blockcounter;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public Counter getScore() {
        return score;
    }

    /**
     * the function returns the game environment.
     *
     * @return environment (Environment).
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * the function returns the sprites.
     *
     * @return sprites (Sprites).
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * the function adds collidable objects to the collidable list.
     *
     * @param c (Collidable).
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * the function adds Sprite objects to the Sprite list.
     *
     * @param s (Sprite).
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * the function Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        sprites.addSprite(levelInfo.getBackground());
        this.blockcounter = new Counter(0);
        this.ballcounter = new Counter(this.levelInfo.numberOfBalls());
        //the screen's frame blocks
        Block scoreBlock = new Block(0, 0, Constants.SCREEN_WIDTH, Constants.BORDER_WIDTH);
        Block block1 = new Block(Constants.BORDER_WIDTH, Constants.BORDER_WIDTH, Constants.SCREEN_WIDTH
                - Constants.BORDER_WIDTH, Constants.BORDER_WIDTH);
        Block block2 = new Block(0, Constants.BORDER_WIDTH, Constants.BORDER_WIDTH, Constants.SCREEN_HEIGHT);
        Block block3 = new Block(Constants.SCREEN_WIDTH - Constants.BORDER_WIDTH, Constants.BORDER_WIDTH,
                Constants.BORDER_WIDTH, Constants.SCREEN_HEIGHT - Constants.BORDER_WIDTH);
        BallRemover removerB = new BallRemover(this, getBallsCounter());
        Block block4 = new Block(0, Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH, Constants.BORDER_WIDTH);
        scoreBlock.addToGame(this);
        block1.addToGame(this);
        block2.addToGame(this);
        block3.addToGame(this);
        //score
        scoreBlock.setColor(Color.lightGray);
        ScoreIndicator indicator = new ScoreIndicator(this.score);
        indicator.addToGame(this);
        block4.addToGame(this);
        block4.addHitListener(removerB);
        LevelName levelName = new LevelName(this.levelInfo.levelName());
        levelName.addToGame(this);
        for (Block block : this.levelInfo.blocks()) {
            BlockRemover remover = new BlockRemover(this, getCounter());
            ScoreTrackingListener scoring = new ScoreTrackingListener(getScore());
            blockcounter.increase(1);
            block.addHitListener(remover);
            block.addHitListener(scoring);
            block.addToGame(this);
        }

    }

    /**
     * Gets blocks to remove.
     *
     * @return the blocks to remove
     */
    public int getBlocksToRemove() {
        return this.levelInfo.numberOfBlocksToRemove();
    }

    /**
     * the function removes Sprite objects from the Sprite list.
     *
     * @param s (Sprite).
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }
    /**
     * returning boolean that indicates if the animation to should stop.
     * @return boolean
     */
    public boolean shouldStop() {
        return !this.running;
    }
    /**
     * draw the animation.
     *
     * @param d DrawSurface
     */
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (blockcounter.getValue() == 0) {
            score.increase(100);
            this.running = false;
        }
        if (ballcounter.getValue() == 0) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY,
                    new EndScreen(this.score.getValue(), this.runner, this.gui.getKeyboardSensor())));
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    this.keyboard.SPACE_KEY, new PauseScreen(this.keyboard)));
        }
    }

    /**
     * the function Run the game -- start the animation loop.
     */
    public void run() {
        this.createBallsOnTopOfPaddle(); // or a similar method
        runner.setFramesPerSecond((Constants.NUM_OF_SEC * 1000) / 3);
        this.runner.run(new CountdownAnimation(Constants.NUM_OF_SEC,
                Constants.COUNT_FROM, sprites)); // countdown before turn starts.
        runner.setFramesPerSecond(Constants.MILI_SEC);
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    private void createBallsOnTopOfPaddle() {
        Ball[] balls = new Ball[levelInfo.numberOfBalls()];
        for (int i = 0; i < levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(400, 555, 4, Color.black);
            ball.setGameEnvironment(getEnvironment());
            ball.setVelocity(levelInfo.initialBallVelocities().get(i));
            ball.addToGame(this);
            balls[i] = ball;
        }
        //creates the blocks.
        Paddle paddle = new Paddle(gui, RIGHT_LIMIT, LEFT_LIMIT, balls, levelInfo.paddleSpeed(),
                levelInfo.paddleWidth());
        paddle.addToGame(this);

    }

    /**
     * Gets blocks list size.
     *
     * @return the blocks list size
     */
    public int getBlocksListSize() {
        return this.levelInfo.blocks().size();
    }

}