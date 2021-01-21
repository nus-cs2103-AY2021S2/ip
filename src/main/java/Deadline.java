public class Deadline extends Task {
    private String deadline;

    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    /**
     * @return a string describing the deadline task
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}