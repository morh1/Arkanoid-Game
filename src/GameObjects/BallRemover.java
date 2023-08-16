package GameObjects;

import Game.GameLevel;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel         the game
     * @param removedBalls the removed balls
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        setGame(gameLevel);
        remainingBalls = removedBalls;
    }

    private void setGame(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    /**
     *  Balls that are hit should be removed
     *  from the game.
     *
     * @param beingHit (Block)
     * @param hitter (Ball)
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        hitter.removeHitListener(this);
        remainingBalls.decrease(1);
    }
}
