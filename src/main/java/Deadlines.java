public class Deadlines extends Task {
    String deadLine;

    public Deadlines(boolean isDone, String eventName, String deadLine) {

        super(isDone, eventName, "D");
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {

        return "[D] " + super.toString() + " (by: " + deadLine + ")";
    }
}
