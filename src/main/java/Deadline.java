public class Deadline extends Task {
    private String deadline;

    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description
                + " (by: " + deadline + ")";
    }
}
