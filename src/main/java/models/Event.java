package models;

public class Event extends Todo {
    String eventTime;

    public Event(String message, String eventTime) {
        super(message);
        this.eventTime = eventTime;
    }

    public Event(String message, boolean isDone, String eventTime) {
        super(message, isDone);
        this.eventTime = eventTime;
    }

    @Override
    public String getMessage() {
        return String.format("[E][%s] %s (at:%s)", this.getIsDoneIcon(), this.message, this.eventTime);
    }

    public Event markAsDone() {
        return new Event(this.message, true, this.eventTime);
    }
}
