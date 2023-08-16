package GameObjects;

/**
 * The interface Hit notifier.
 */
public interface HitNotifier {
    /**
     * Add hit listener.
     *
     * @param hl the hl
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the hl
     */
    void removeHitListener(HitListener hl);
}


