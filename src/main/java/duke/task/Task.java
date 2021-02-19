package duke.task;

/**
 * Represents a task item that can be added and listed in Duke.
 *
 * @author Aaron Saw Min Sern
 */
public abstract class Task {
    protected boolean isDone;
    protected final String description;

    /**
     * Default constructor for subclasses of Task.
     *
     * @param isDone      the status of the task item
     * @param description the description for the task item
     */
    protected Task(final boolean isDone, final String description) {
        this.isDone = isDone;
        this.description = description;
    }

    public abstract String encode();


    /**
     * Returns a tick if this Task object is finished and a cross otherwise.
     *
     * @return the status icon based on the status of this Task object.
     */
    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }

    /**
     * Returns true if the marking of completion status of the Task object is
     * successful.
     *
     * @return true when the task was not already marked as done.
     */
    public boolean markAsDone() {
        boolean wasDone = this.isDone;
        this.isDone = true;
        return !wasDone;
    }

    /**
     * Returns true if this task description contains the keyword ignoring case.
     *
     * @param keyword the keyword to be tested with
     * @return true if this task description matches the keyword
     */
    public boolean isMatching(String keyword) {
        return containsIgnoreCase(description, keyword);
    }

    private static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }

        final int length = searchStr.length();
        if (length == 0) {
            return true;
        }

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the string represenatation of this Task instance
     *
     * @return the string representation of this Task instance
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
