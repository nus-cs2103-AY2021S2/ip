package duke.command;

/**
 * Creates an Event task.
 */
public class EventCommand extends Command{
    String description;
    String eventTime;

    public EventCommand(String description, String eventTime) {
        super("event");
        this.description = description;
        this.eventTime = eventTime;
    }

    /**
     * Returns description of event task.
     * @return description of event task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns event time of task.
     * @return event time of task
     */
    public String getEventTime() {
        return eventTime;
    }

    @Override
    public void run() {

    }
}
