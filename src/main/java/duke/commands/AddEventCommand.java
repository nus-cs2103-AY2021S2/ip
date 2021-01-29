package duke.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;

/**
 * Adds an event to the task list.
 */
public class AddEventCommand extends Command {
    private static final Pattern ADD_EVENT_KEYWORD = Pattern.compile("(?i)event\\b");
    private static final Pattern ADD_EVENT_DESC = Pattern.compile("(?i)event\\s+(\\w.*)");
    private static final Pattern ADD_EVENT_PERIOD = Pattern.compile("(?i)event\\s+(\\w.*)\\s+/at\\s+(\\w.*)");

    private final String taskDesc;
    private final String period;

    private AddEventCommand(String taskDesc, String period) {
        this.taskDesc = taskDesc;
        this.period = period;
    }

    /**
     * Returns true if the input string starts with the keyword event. False otherwise.
     *
     * @param input Command as a string.
     * @return true if the input string starts with the keyword event. False otherwise.
     */
    public static boolean isAddEventCommand(String input) {
        return ADD_EVENT_KEYWORD.matcher(input).find();
    }

    /**
     * Parses the input command string as an add event command and returns an AddEventCommand if successful.
     * Throws a DukeException with a relevant message if parsing fails.
     *
     * @param input Command as a string.
     * @return The parsed AddEventCommand.
     * @throws DukeException If parsing fails.
     */
    public static AddEventCommand parseAddEventCommand(String input) throws DukeException {
        // check description exists
        Matcher descMatcher = ADD_EVENT_DESC.matcher(input);
        if (!descMatcher.find()) {
            throw new DukeException("The description of an event cannot be empty!\n"
                    + "Expected format: event <DESCRIPTION> /at <PERIOD>");
        }

        // check period exists
        Matcher periodMatcher = ADD_EVENT_PERIOD.matcher(input);
        if (!periodMatcher.find()) {
            throw new DukeException("An event must have a period!\n"
                    + "Expected format: event <DESCRIPTION> /at <PERIOD>");
        }

        String taskDesc = periodMatcher.group(1);
        String period = periodMatcher.group(2);

        return new AddEventCommand(taskDesc, period);
    }

    /**
     * Adds an event to tasks and returns a CommandResult indicating success.
     *
     * @param tasks The task list to add to.
     * @param storage The storage used by Duke (unused).
     * @return A CommandResult indicating success.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        Event event = new Event(taskDesc, period);
        tasks.add(event);
        String feedback = String.format("Got it. I've added this task:\n"
                        + "%s\n"
                        + "Now you have %d task(s) in the list.",
                event.toString(), tasks.taskCount());
        return new CommandResult(feedback);
    }
}
