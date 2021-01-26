package duke.tasks;

public class Deadline extends Task {
    protected final String deadline;

    public Deadline(String description, String deadline) {
        super(description, "[D]");
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        String str = deadline.strip();
        return super.toString() + "(by: " + str + ")";
    }
}
