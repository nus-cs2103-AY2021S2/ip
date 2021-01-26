public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getSaveString() {
        if (this.isDone()) {
            return String.format("deadline [isDone] %s /by %s\n", description, deadline);
        } else {
            return String.format("deadline %s /by %s\n", description, deadline);
        }
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatus(), description, deadline);
    }
}