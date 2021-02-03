package duke.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Lists the tasks in the task list.
 *
 * @author Benedict Khoo
 */
public class ListCommand extends Command {
    private static final Pattern LIST_KEYWORD = Pattern.compile("(?i)list\\b");
    private static final Pattern LIST_ONLY = Pattern.compile("(?i)list(\\s*)$");

    private ListCommand() {
    }

    /**
     * Returns true if the input string starts with the keyword list. False otherwise.
     *
     * @param input Command as a string.
     * @return true if the input string starts with the keyword list. False otherwise.
     */
    public static boolean isListCommand(String input) {
        return LIST_KEYWORD.matcher(input).find();
    }

    /**
     * Parses the input command string as a list command and returns a ListCommand if successful.
     * Throws a DukeException with a relevant message if parsing fails.
     *
     * @param input Command as a string.
     * @return The parsed ListCommand.
     * @throws DukeException If parsing fails.
     */
    public static ListCommand parseListCommand(String input) throws DukeException {
        Matcher listMatcher = LIST_ONLY.matcher(input);
        if (!listMatcher.find()) {
            throw new DukeException("Please do not include any arguments after the list command.\n"
                    + "Expected format: list");
        }

        return new ListCommand();
    }

    /**
     * Returns a CommandResult with the list of tasks as a message for the user.
     *
     * @param tasks   The task list to list.
     * @param storage The storage used by Duke (unused).
     * @return A CommandResult with the list of tasks as a message for the user.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        StringBuilder feedback = new StringBuilder();

        if (tasks.taskCount() == 0) {
            feedback.append("No tasks currently!");
        } else {
            for (int i = 0; i < tasks.taskCount(); i++) {
                Task t = tasks.getAt(i);
                int index = i + 1;
                feedback.append(String.format("%d.%s", index, t));
                if (i < tasks.taskCount() - 1) {
                    feedback.append("\n");
                }
            }
        }

        return new CommandResult(feedback.toString());
    }
}
