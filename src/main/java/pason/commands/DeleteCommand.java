package pason.commands;

import pason.storage.Storage;
import pason.tasks.TaskList;
import pason.ui.Ui;

public class DeleteCommand extends Command {
    protected int index;
    public DeleteCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            ui.printMessage(tasks.deleteTask(this.index));
        } catch(Exception e) {
            ui.printMessage(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}