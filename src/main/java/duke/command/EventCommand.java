package duke.command;

import java.io.File;
import java.io.IOException;

import duke.duke.Duke;
import duke.storage.Storage;

/**
 * Creates an Event task.
 */
public class EventCommand extends Command {
    private final String description;
    private final String eventTime;

    /**
     * Creates an {@code EventCommand} object with a task description and event time component.
     * @param description task description
     * @param eventTime time of event
     */
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
    public String run(File file, Duke bot) throws IOException {
        String output = bot.addTask(getDescription(), getCommand(), getEventTime());
        Storage.saveFile(file, bot);
        return output;
    }
}
