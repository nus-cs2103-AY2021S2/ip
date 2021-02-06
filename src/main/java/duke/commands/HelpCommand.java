package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public HelpCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }

    @Override
    public String execute() {
        String validCommandMsg = "Valid commands include the following:\n" + "'todo', 'deadline', 'event', 'list', 'delete', 'bye'\n";
        String suggestionMsg = "Please start your input with one of the above commands!";
        return validCommandMsg + suggestionMsg;
    }
}
