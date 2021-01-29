package duke.commands;

import java.util.regex.Pattern;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Signals Duke to exit.
 */
public class ExitCommand extends Command {
    private static final Pattern EXIT_KEYWORD = Pattern.compile("(?i)bye\\b");

    /**
     * Constructs an exit command.
     */
    public ExitCommand() {
    }

    /**
     * Returns true if the input string starts with the keyword exit. False otherwise.
     *
     * @param input Command as a string.
     * @return true if the input string starts with the keyword exit. False otherwise.
     */
    public static boolean isExitCommand(String input) {
        return EXIT_KEYWORD.matcher(input).find();
    }

    /**
     * Returns a new CommandResult that signals Duke to exit.
     *
     * @param tasks The task list used by Duke (unused).
     * @param storage The storage used by Duke (unused).
     * @return A CommandResult that signals Duke to exit.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(null, true);
    }
}
