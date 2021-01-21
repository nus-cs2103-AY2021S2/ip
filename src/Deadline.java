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

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), time);
    }
}
