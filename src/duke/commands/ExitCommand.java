package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

import java.util.regex.Pattern;

public class ExitCommand extends Command {
    private static final Pattern EXIT_KEYWORD = Pattern.compile("(?i)bye\\b");

    public ExitCommand() {
    }

    public static boolean isExitCommand(String input) {
        return EXIT_KEYWORD.matcher(input).find();
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(null, true);
    }
}
