package yoda.task;

public class Event extends Task{
    public Event(String description, String at) {
        super(description);
        setDateTime(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "at " + formatDateTime();
    }
}
