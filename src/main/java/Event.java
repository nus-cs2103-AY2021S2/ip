public class Event extends Task {
    String dateTime;

    public Event(boolean markAsDone, String taskName, String dateTime) {
        super('E', markAsDone, taskName);
        this.dateTime = dateTime;
    }

    @Override
    public String generateFileFormatString() {
        return super.generateFileFormatString() + " // " + this.dateTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.dateTime);
    }
}
