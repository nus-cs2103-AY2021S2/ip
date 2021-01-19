package commands;

import duke.TaskManager;
import duke.Ui;
import exceptions.DukeException;

public class DoneCommand extends Command {
    private int taskIndex;

    public DoneCommand(int index) {
        this.taskIndex = index;
    }

    public void execute(Ui ui, TaskManager tm) throws DukeException {
        tm.markTaskAsDone(taskIndex);
    }
}
