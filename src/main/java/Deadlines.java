public class Deadlines extends Task {
    String deadLine;

    public Deadlines(String eventName, String deadLine) {

        super(false, eventName);
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {

        return "[D] " + super.toString() + "(by: " + deadLine + ")";
    }
}
