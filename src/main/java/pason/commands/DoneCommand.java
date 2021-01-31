package pason.commands;

import pason.storage.Storage;
import pason.tasks.TaskList;
import pason.ui.Ui;

public class DoneCommand extends Command {
    protected int index;
    /**
     * Initialises the DoneCommand.
     */
    public DoneCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    /**
     * Executes the command.
     */
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            ui.printMessage(tasks.doneTask(this.index));
        } catch (Exception e) {
            ui.printMessage(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
