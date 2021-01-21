public class Deadline extends Task {
    private String deadline;

    Deadline(String name, String deadLine) {
        super(name);
        this.deadline = deadLine;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
