package duke.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Adds a ToDo to the task list.
 */
public class AddToDoCommand extends Command {
    private static final Pattern ADD_TODO_KEYWORD = Pattern.compile("(?i)todo\\b");
    private static final Pattern ADD_TODO_DESC = Pattern.compile("(?i)todo\\s+(\\w.*)");

    private final String taskDesc;

    private AddToDoCommand(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    /**
     * Returns true if the input string starts with the keyword todo. False otherwise.
     *
     * @param input Command as a string.
     * @return true if the input string starts with the keyword todo. False otherwise.
     */
    public static boolean isAddToDoCommand(String input) {
        return ADD_TODO_KEYWORD.matcher(input).find();
    }

    /**
     * Parses the input command string as an add todo command and returns an AddToDoCommand if successful.
     * Throws a DukeException with a relevant message if parsing fails.
     *
     * @param input Command as a string.
     * @return The parsed AddToDoCommand.
     * @throws DukeException If parsing fails.
     */
    public static AddToDoCommand parseAddToDoCommand(String input) throws DukeException {
        Matcher toDoMatcher = ADD_TODO_DESC.matcher(input);
        if (!toDoMatcher.find()) {
            // Matched command but invalid argument
            throw new DukeException("The description of a todo cannot be empty!\n"
                    + "Expected format: todo <DESCRIPTION>");
        }

        String taskDesc = toDoMatcher.group(1);

        return new AddToDoCommand(taskDesc);
    }

    /**
     * Adds a todo to tasks and returns a CommandResult indicating success.
     *
     * @param tasks The task list to add to.
     * @param storage The storage used by Duke (unused).
     * @return A CommandResult indicating success.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        ToDo toDo = new ToDo(taskDesc);
        tasks.add(toDo);
        String feedback = String.format("Got it. I've added this task:\n"
                        + "%s\n"
                        + "Now you have %d task(s) in the list.",
                toDo.toString(), tasks.taskCount());
        return new CommandResult(feedback);
    }
}
