package surrealchat;

public class Pair<T,U> {
    protected final T firstItem;
    protected final U secondItem;

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
