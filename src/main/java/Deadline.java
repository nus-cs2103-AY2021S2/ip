public class Deadline extends Task{
    String deadLine;

    Deadline(String taskName, String deadLine) {
        super(taskName);
        this.deadLine = deadLine;
    }

    Deadline(String taskName, boolean done, String deadLine) {
        super(taskName, done);
        this.deadLine = deadLine;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadLine + ")";
    }
}
