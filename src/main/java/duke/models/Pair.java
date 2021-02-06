package duke.models;

public class Pair<T, U> {
    /** first attribute of pair */
    private final T first;
    /** second attribute of pair */
    private final U second;

    /**
     * Creates a new generic Pair which contains multiple types
     * @param first first attribute of pair
     * @param second second attribute of pair
     */
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Get first attribute contained in Pair
     * @return first attribute of Pair
     */
    public T getFirst() {
        return this.first;
    }

    /**
     * Get second attribute contained in Pair
     * @return second attribute of Pair
     */
    public U getSecond() {
        return this.second;
    }
}
