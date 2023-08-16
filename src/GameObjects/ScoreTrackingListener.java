package GameObjects;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     *This method is called whenever the beingHit object is hit.
     *The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }

    /**
     * Gets current score.
     *
     * @return the current score
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }
}
