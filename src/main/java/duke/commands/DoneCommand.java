package duke.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Marks a task as done.
 *
 * @author Benedict Khoo
 */
public class DoneCommand extends Command {
    private static final Pattern DONE_KEYWORD = Pattern.compile("(?i)(?:^done|^d)\\b");
    private static final Pattern DONE_INDEX = Pattern.compile("(?i)(?:^done|^d)\\s+(\\d+)$");
    private static final String ACCEPTED_FORMAT_MSG = "Accepted formats:\n"
            + "  done <TASK NUMBER>\n"
            + "  d <TASK NUMBER>";

    private final String argStr;

    private DoneCommand(String argStr) {
        this.argStr = argStr;
    }

    /**
     * Returns true if the input string starts with the keyword done. False otherwise.
     *
     * @param input Command as a string.
     * @return true if the input string starts with the keyword done. False otherwise.
     */
    public static boolean isDoneCommand(String input) {
        return DONE_KEYWORD.matcher(input).find();
    }

    /**
     * Parses the input command string as a done command and returns a DoneCommand if successful.
     * Throws a DukeException with a relevant message if parsing fails.
     *
     * @param input Command as a string.
     * @return The parsed DoneCommand.
     * @throws DukeException If parsing fails.
     */
    public static DoneCommand parseDoneCommand(String input) throws DukeException {
        Matcher doneMatcher = DONE_INDEX.matcher(input);
        if (!doneMatcher.find()) {
            throw new DukeException("A done command must specify a task number.\n"
                    + ACCEPTED_FORMAT_MSG);
        }

        String argStr = doneMatcher.group(1);

        return new DoneCommand(argStr);
    }

    /**
     * Marks a task in tasks as done and returns a CommandResult indicating success or failure.
     *
     * @param tasks   The task list to get the task from.
     * @param storage The storage used by Duke (unused).
     * @return A CommandResult indicating success or failure.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        String feedback;
        try {
            int arg = Integer.parseInt(argStr);
            if (arg < 1 || arg > tasks.taskCount()) {
                // Argument out of range
                feedback = String.format("Task %d does not exist!\n"
                        + "Valid task numbers are 1 to %d.", arg, tasks.taskCount());
            } else {
                // Valid argument in range
                int index = arg - 1;
                Task t = tasks.getAt(index);
                t.markAsDone();

                feedback = String.format("Nice! I've marked this task as done:\n"
                        + "%s", t.toString());
            }
        } catch (NumberFormatException nfe) {
            // Argument of wrong type
            feedback = String.format("Illegal argument: '%s'. Expected integer.\n"
                    + "Valid task numbers are 1 to %d.", argStr, tasks.taskCount());
        }

        return new CommandResult(feedback);
    }
}
