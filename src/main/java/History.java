import java.util.LinkedList;

public class History<T> {
    private final LinkedList<T> histories;

    public History() {
        this.histories = new LinkedList<>();
    }

    public void add(T item) {
        this.histories.add(item);
    }

    public T getLast() throws DukeException {
        if (this.histories.size() == 0) {
            throw new DukeException("Sorry!!! There are no histories!");
        }
        return histories.pollLast();
    }

    public T getMultipleItems(int numberOfItems) throws DukeException {
        if (this.histories.size() == 0) {
            throw new DukeException("Sorry!!! There are no histories!");
        }
        T current = this.histories.pollLast();
        while (numberOfItems > 0 && this.histories.size() > 0) {
            current = this.histories.pollLast();
            numberOfItems--;
        }
        return current;
    }

    public int compareNumberOfHistoriesWithInput(int numberOfUndos) {
        return Math.min(this.histories.size(), numberOfUndos);
    }

    public boolean isEmpty() {
        return this.histories.isEmpty();
    }
}
