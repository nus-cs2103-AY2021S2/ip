import java.util.LinkedList;

/**
 * A class that represents history of item type T after every change
 * @param <T>
 */
public class History<T> {
    private final LinkedList<T> histories;

    /**
     * Constructor of history
     */
    public History() {
        this.histories = new LinkedList<>();
    }

    /**
     * Add an item into the history
     * @param item The item wanted to be added
     */
    public void add(T item) {
        this.histories.add(item);
    }

    /**
     * Get the last added item of the history
     * @return The last item from the history
     * @throws DukeException If the history is empty
     */
    public T getLast() throws DukeException {
        if (this.histories.size() == 0) {
            throw new DukeException("Sorry!!! There are no histories!");
        }
        return histories.pollLast();
    }

    /**
     * Get the min(n-item, historySize) from the history
     * @param numberOfItems The index
     * @return The N-th position item from the back
     * @throws DukeException If the history is empty
     */
    public T getMultipleItems(int numberOfItems) throws DukeException {
        if (this.histories.size() == 0) {
            throw new DukeException("Sorry!!! There are no histories!");
        }
        int numberOfUndos = compareNumberOfHistoriesWithInput(numberOfItems);
        T current = this.histories.peekLast();
        while (numberOfUndos > 0) {
            current = this.histories.pollLast();
            numberOfUndos--;
        }
        return current;
    }

    /**
     * Compare index
     * @param numberOfUndos Number given by the user
     * @return minimum of (numberOfUndos, histories.size())
     */
    public int compareNumberOfHistoriesWithInput(int numberOfUndos) {
        return Math.min(this.histories.size(), numberOfUndos);
    }

    /**
     * Check whether histories is empty or not
     * @return Boolean representing whether the histories is empty or not
     */
    public boolean isEmpty() {
        return this.histories.isEmpty();
    }
}
