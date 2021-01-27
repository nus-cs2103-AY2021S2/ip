package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoneCommand extends Command {
    private static final Pattern DONE_KEYWORD = Pattern.compile("(?i)done\\b");
    private static final Pattern DONE_INDEX = Pattern.compile("(?i)done\\s+(\\d+)$");

    private final String argStr;

    private DoneCommand(String argStr) {
        this.argStr = argStr;
    }

    public static boolean isDoneCommand(String input) {
        return DONE_KEYWORD.matcher(input).find();
    }

    public static DoneCommand parseDoneCommand(String input) throws DukeException {
        Matcher doneMatcher = DONE_INDEX.matcher(input);
        if (!doneMatcher.find()) {
            throw new DukeException("A done command must specify a task number.\n"
                    + "Expected format: done <TASK NUMBER>");
        }

        String argStr = doneMatcher.group(1);

        return new DoneCommand(argStr);
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
