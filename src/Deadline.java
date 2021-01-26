public class Deadline extends Task {
    private String time;

    /**
     * Constructs a deadline task.
     * @param description a description of the deadline task.
     * @param time the time the task is due.
     */
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructs a deadline task.
     * @param description a description of the task.
     * @param time the time the task is due.
     * @param isDone the completion status of the task.
     */
    public Deadline(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), time);
    }
}
