package task;

public class Deadline extends Task{
    String deadLine;

    public Deadline(String taskName, String deadLine) {
        super(taskName);
        this.deadLine = deadLine;
    }

    public Deadline(String taskName, boolean done, String deadLine) {
        super(taskName, done);
        this.deadLine = deadLine;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadLine + ")";
    }

    public String parseToCSVRow() {
        return "D," + super.isDone() + "," + super.getTaskName() + "," + this.deadLine;
    }
}
