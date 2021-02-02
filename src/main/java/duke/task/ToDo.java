package duke.task;

/**
 * Represents a todo task item without a specific deadline.
 *
 * @author Aaron Saw Min Sern
 */
public class ToDo extends Task {
    /**
     * Sole constructor for class ToDo.
     *
     * @param description the description of this ToDo instance
     */
    public ToDo(String description) {
        super(false, description);
    }

    /**
     * Returns the string representation of this ToDo instance.
     *
     * @return the string representation of this ToDo instance
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Returns the encoded representation for this ToDo instance.
     *
     * @return the encoded representation of this ToDo instance
     */
    @Override
    public String encode() {
        return String.format("T | %s | %s", this.isDone ? "1" : "0", this.description);
    }

    /**
     * Returns true if two ToDo items are equivalent.
     *
     * @param o the other object to be compared
     * @return true if two ToDo items are equivalent
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ToDo)) {
            return false;
        }
        ToDo other = (ToDo) o;
        return this.description.equals(other.description) && this.isDone == other.isDone;
    }

    /**
     * Returns the hashcode of this ToDo instance.
     *
     * @return hashcode of this ToDo instance
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + (isDone ? 1 : 0);
        return result;
    }
}
