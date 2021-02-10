package duke.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Deletes a task from the task list.
 *
 * @author Benedict Khoo
 */
public class DeleteCommand extends Command {
    private static final Pattern DELETE_KEYWORD = Pattern.compile("(?i)(?:^delete|^del|^rm)\\b");
    private static final Pattern DELETE_INDEX = Pattern.compile("(?i)(?:^delete|^del|^rm)\\s+(\\d+)$");
    private static final String ACCEPTED_FORMAT_MSG = "Accepted formats:\n"
            + "  delete <TASK NUMBER>\n"
            + "  del <TASK NUMBER>\n"
            + "  rm <TASK NUMBER>";

    private final String argStr;

    private DeleteCommand(String argStr) {
        this.argStr = argStr;
    }

    /**
     * Returns true if the input string starts with the keyword delete. False otherwise.
     *
     * @param input Command as a string.
     * @return true if the input string starts with the keyword delete. False otherwise.
     */
    public static boolean isDeleteCommand(String input) {
        return DELETE_KEYWORD.matcher(input).find();
    }

    /**
     * Parses the input command string as a delete command and returns a DeleteCommand if successful.
     * Throws a DukeException with a relevant message if parsing fails.
     *
     * @param input Command as a string.
     * @return The parsed DeleteCommand.
     * @throws DukeException If parsing fails.
     */
    public static DeleteCommand parseDeleteCommand(String input) throws DukeException {
        Matcher deleteMatcher = DELETE_INDEX.matcher(input);
        if (!deleteMatcher.find()) {
            throw new DukeException("A delete command must specify a task number.\n"
                    + ACCEPTED_FORMAT_MSG);
        }

        String argStr = deleteMatcher.group(1);

        return new DeleteCommand(argStr);
    }

    /**
     * Deletes a task from tasks and returns a CommandResult indicating success or failure.
     *
     * @param tasks   The task list to delete from.
     * @param storage The storage used by Duke (unused).
     * @return A CommandResult indicating success or failure.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        String feedback;

        if (tasks.taskCount() == 0) {
            feedback = "There are no tasks to delete.";
            return new CommandResult(feedback);
        }

        int arg;
        try {
            arg = Integer.parseInt(argStr);
        } catch (NumberFormatException nfe) {
            // Argument of wrong type
            feedback = String.format("Illegal argument: '%s'. Expected integer.\n"
                    + "Valid task numbers are 1 to %d.", argStr, tasks.taskCount());
            return new CommandResult(feedback);
        }

        if (arg < 1 || arg > tasks.taskCount()) {
            // Argument out of range
            feedback = String.format("Task %d does not exist!\n"
                    + "Valid task numbers are 1 to %d.", arg, tasks.taskCount());
            return new CommandResult(feedback);
        }

        int index = arg - 1;
        Task t = tasks.getAt(index);
        tasks.removeAt(index);

        feedback = String.format("Noted. I've removed this task:\n"
                        + "%s\n"
                        + "Now you have %d task(s) in the list.",
                t.toString(), tasks.taskCount());
        return new CommandResult(feedback);
    }
}
