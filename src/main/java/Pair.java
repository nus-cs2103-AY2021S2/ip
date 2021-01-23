public class Pair<T,U> {
    public final T firstItem;
    public final U secondItem;

    public Pair(T firstItem, U secondItem) {
        this.firstItem = firstItem;
        this.secondItem = secondItem;
    }

    public T getFirstItem() {
        return this.firstItem;
    }

    public U getSecondItem() {
        return this.secondItem;
    }
}
