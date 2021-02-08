package duke.command;

import duke.Ui;
import duke.Storage;
import duke.tasks.TaskList;

public class ByeCommand extends Command {
    public static final boolean IS_EXIT = true;

    /**
     * Constructor method
     */
    public ByeCommand() {
        super(IS_EXIT);
    }

    /**
     * Execute method for ByeCommand
     * Prints bye message and exits the bot.
     * @param tasks The tasks in the TaskList.
     * @param ui Standard UI object
     * @param storage Standard storage object
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printBye();
    }
}

