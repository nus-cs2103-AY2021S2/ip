public class Event extends Command {
    public String eventTime;

    public Event(String commandDescription, String eventTime) {
        super(commandDescription);
        this.isDone = false;
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " | at: " + eventTime;
    }
}