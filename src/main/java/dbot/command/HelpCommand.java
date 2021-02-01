package dbot.command;

import dbot.ui.Ui;
import dbot.storage.Storage;
import dbot.tasklist.TaskList;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public HelpCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelpMessage();
    }

    @Override
    public void quietExecute(TaskList tasks, Storage storage) {
        throw new IllegalArgumentException("The 'help' command cannot be used quietly.");
    }
}
