package duke.util;

/**
 * Utility class to represent and contain a pair of any 2 objects.
 */
public class Tuple<S, T> {
    private final S first;
    private final T second;

    /**
     * Constructs a tuple with the 2 given objects.
     * @param first an object of type S
     * @param second an object of type T
     */
    public Tuple(S first, T second) {
        this.first = first;
        this.second = second;
    }

    public S getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }
}
