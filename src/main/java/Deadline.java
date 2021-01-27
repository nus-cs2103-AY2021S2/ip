public class Deadline extends Task {
    String deadLine;

    public Deadline(String taskName, String deadLine) {
        super(taskName);
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadLine + ")";
    }

    @Override
    public String generateDataString() {
        return "deadline" + taskName + deadLine + (done ? "done" : "notDone");
    }
}
