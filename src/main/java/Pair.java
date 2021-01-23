package surrealchat;

/**
 * Stores 2 different types of objects.
 * @param <T> Generic type for first object.
 * @param <U> Generic type for second object.
 */
public class Pair<T,U> {
    protected final T firstItem;
    protected final U secondItem;

    /**
     * Creates a new Pair instance.
     * @param firstItem Any object considered first. Usually a String for tagging.
     * @param secondItem Any object considered second.
     */
    public Pair(T firstItem, U secondItem) {
        this.firstItem = firstItem;
        this.secondItem = secondItem;
    }

    /**
     * Obtains the first item.
     * @return First item.
     */
    public T getFirstItem() {
        return this.firstItem;
    }

    /**
     * Obtains the second item.
     * @return Second item.
     */
    public U getSecondItem() {
        return this.secondItem;
    }
}
