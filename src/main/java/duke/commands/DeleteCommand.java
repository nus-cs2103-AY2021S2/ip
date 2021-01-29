package duke.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private static final Pattern DELETE_KEYWORD = Pattern.compile("(?i)delete\\b");
    private static final Pattern DELETE_INDEX = Pattern.compile("(?i)delete\\s+(\\d+)$");

    private final String argStr;


    private DeleteCommand(String argStr) {
        this.argStr = argStr;
    }

    public static boolean isDeleteCommand(String input) {
        return DELETE_KEYWORD.matcher(input).find();
    }

    public static DeleteCommand parseDeleteCommand(String input) throws DukeException {
        Matcher deleteMatcher = DELETE_INDEX.matcher(input);
        if (!deleteMatcher.find()) {
            throw new DukeException("A delete command must specify a task number.\n"
                    + "Expected format: delete <TASK NUMBER>");
        }

        String argStr = deleteMatcher.group(1);

        return new DeleteCommand(argStr);
    }

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
                tasks.removeAt(index);

                feedback = String.format("Noted. I've removed this task:\n"
                                + "%s\n"
                                + "Now you have %d task(s) in the list.",
                        t.toString(), tasks.taskCount());
            }
        } catch (NumberFormatException nfe) {
            // Argument of wrong type
            feedback = String.format("Illegal argument: '%s'. Expected integer.\n"
                    + "Valid task numbers are 1 to %d.", argStr, tasks.taskCount());
        }

        return new CommandResult(feedback);
    }
}
