public class Event extends Task {
    public Event(boolean isDone, String description) {
        super("E", isDone, description);
    }

    public String modifiedDescription() {
        String dateTime = description.split("/at ")[1];
        dateTime = "(at: " + dateTime + ")";
        return description.substring(0, description.indexOf('/')) + dateTime;
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + modifiedDescription();
    }
}
