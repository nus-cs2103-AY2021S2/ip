package duke.command;

import java.io.File;
import java.io.IOException;

import duke.duke.Duke;
import duke.exceptions.InvalidArgumentException;
import duke.storage.Storage;

/**
 * Creates an Event task.
 */
public class EventCommand extends Command {
    private static final String usageMessage = "Command: event <task_description> /at <date>\n"
            + "Description: Adds an event task with an event time to task list\n";
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

    /**
     * Validates arguments of EventCommand.
     * @param userInput User input as String.
     * @return A String array with task description and event time.
     * @throws InvalidArgumentException If task description or event time is missing from input.
     */
    public static String[] validateArgument(String userInput) throws InvalidArgumentException {
        String[] userInputArr = userInput.split("/at");
        if (userInputArr.length == 1) {
            throw new InvalidArgumentException("Please input task due date using '/at (date)'!\n");
        }
        String[] s = userInputArr[0].split(" ", 2);

        if (s.length == 1) {
            throw new InvalidArgumentException("Please input task description!\n");
        }

        return userInputArr;
    }

    public static String getUsageMessage() {
        return usageMessage;
    }

    @Override
    public String run(File file, Duke bot) throws IOException {
        String output = bot.addTask(getDescription(), getCommand(), getEventTime());
        Storage.saveFile(file, bot);
        return output;
    }
}
