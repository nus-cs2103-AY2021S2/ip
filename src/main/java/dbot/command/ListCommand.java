package dbot.command;

import dbot.ui.Ui;
import dbot.storage.Storage;
import dbot.tasklist.TaskList;

/**
 * A concrete subclass of Command that implements a List Command.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Instantiates a List Command.
     */
    public ListCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasks(tasks);
    }

    @Override
    public void quietExecute(TaskList tasks, Storage storage) {
        throw new IllegalArgumentException("The 'list' command cannot be used quietly.");
    }
}
