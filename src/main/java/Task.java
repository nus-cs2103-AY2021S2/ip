public class Task {
    private boolean done;
    private String info;

    /**
     * Constructor for Task
     * @param info task info
     */
    public Task(String info) {
        done = false;
        this.info = info;
    }
    /**
     * Accessor for task completion state
     * @return completion state
     */
    public boolean getCompletionState() {
        return done;
    }

    /**
     * Accessor for task info
     * @return task info
     */
    public String getTaskInfo() {
        return info;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", done ? '✓' : '✗', info);
    }

    /**
     * Sets the completion state for the task as done
     */
    public void setTaskAsDone() {
        done = true;
    }
}
