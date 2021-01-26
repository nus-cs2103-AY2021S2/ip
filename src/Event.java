public class Event extends Task {
    private String time;

    /**
     * Constructs an event task.
     * @param description a description of the task.
     * @param time the time the task is scheduled to occur.
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructs an event task.
     * @param description a description of the task.
     * @param time the time the task is scheduled to occur.
     * @param isDone the completion status of the task.
     */
    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), time);
    }
}
