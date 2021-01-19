package commands;

import duke.Ui;
import duke.TaskManager;
import exceptions.DukeException;
import exceptions.DukeIndexOutOfRangeException;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int index) {
        this.taskIndex = index;
    }

    public void execute(Ui ui, TaskManager tm) throws DukeException {
        tm.deleteTask(taskIndex);
    }
}
