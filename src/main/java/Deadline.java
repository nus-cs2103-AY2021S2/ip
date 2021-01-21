public class Deadline extends Task{
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String type = "[D]";
        String doneStatus = "[" + getStatusIcon() + "]";
        return type + doneStatus + " " + this.description + "(" + this.deadline + ")";
    }

    String deadline;
}
