package duke.command;

public class EventCommand extends Command{
    String description;
    String eventTime;

    public EventCommand(String description, String eventTime) {
        super("event");
        this.description = description;
        this.eventTime = eventTime;
    }

    public String getDescription() {
        return description;
    }

    public String getEventTime() {
        return eventTime;
    }

    @Override
    public void run() {

    }
}
