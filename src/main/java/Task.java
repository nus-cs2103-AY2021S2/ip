/**
 * Abstract class that represents a Task entry. Contains the name of the task taskName
 * and a boolean done to show whether it is done or not. Has methods to deal with marking tasks as done or not
 * and methods to generate data strings for storage.
 */
public abstract class Task {
    final String taskName;
    boolean done;

    /**
     * Class constructor which creates a Task object with the name set to the specified taskName.
     * Created Task is set to not done by default.
     * @param taskName The specified name of the new Task object.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    /**
     * Marks this Task as done. Returns true.
     * @return Returns true.
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * Marks this Task as not done. Returns false.
     * @return Returns false.
     */
    public void markAsNotDone() {
        this.done = false;
    }

    /**
     * A method to create a neatly formatted String that describes this Task.
     * @return Neatly formatted String representation of this Task.
     */
    @Override
    public String toString() {
        return "[" + (done? "x" : " ") + "] " + taskName;
    }

    /**
     * Abstract method that generates a formatted String for storage read and write purposes.
     * @return Formatted data String to be used by Storage.
     */
    public abstract String generateDataString();
}
