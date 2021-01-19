public class Event extends Task {
    String dateTime;

    public Event(boolean markAsDone, String taskName, String dateTime) {
        super(markAsDone, taskName);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at:%s)", super.toString(), this.dateTime);
    }
}
