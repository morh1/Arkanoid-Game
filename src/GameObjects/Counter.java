package GameObjects;

/**
 * The type Counter.
 */
public class Counter {
    private int counter;

    /**
     * Instantiates a new Counter.
     *
     * @param num the num
     */
    public Counter(int num) {
        setCounter(num);
    }

    /**
     * Increase.
     *
     * @param number the number
     */
// add number to current count.
    public void increase(int number) {
        setCounter(getValue() + number);
    }

    /**
     * Decrease.
     *
     * @param number the number
     */
// subtract number from current count.
    void decrease(int number) {
        setCounter(getValue() - number);
    }

    /**
     * Gets value.
     *
     * @return the value
     */
// get current count.
    public int getValue() {
        return this.counter;
    }

    /**
     * Sets counter.
     *
     * @param counter the counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }
}
