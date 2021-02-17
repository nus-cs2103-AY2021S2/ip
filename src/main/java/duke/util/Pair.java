package duke.util;

/**
 * Stores two different items together.
 */
public class Pair<T, U> {
    private T t;
    private U u;

    /**
     * Creates a Pair with 2 items.
     *
     * @param t First item.
     * @param u Second item.
     */
    public Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    /**
     * Returns first item.
     *
     * @return First item.
     */
    public T getFirst() {
        return t;
    }

    /**
     * Returns second item.
     *
     * @return Second item.
     */
    public U getSecond() {
        return u;
    }
}
