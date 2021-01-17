/**
 * Represent an item in the todo list
 */
public class Item {
    private String name;
    private boolean isDone;

    /**
     * Initialize a new undone Item
     * @param name Name of the item
     */
    public Item(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * Check whether the item is done or not
     * @return true if the item is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Set the status of the item; e.g. done or not done
     * @param isDone the status to set to the item
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
