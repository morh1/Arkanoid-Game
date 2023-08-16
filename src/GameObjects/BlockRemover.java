package GameObjects;

import Game.GameLevel;

/**
 * The type Block remover.
 */
// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        setGame(gameLevel);
        remainingBlocks = removedBlocks;
    }

    private void setGame(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }
    /**
     *  Blocks that are hit should be removed
     *  from the game.
     *
     * @param beingHit (Block)
     * @param hitter (Ball)
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}
