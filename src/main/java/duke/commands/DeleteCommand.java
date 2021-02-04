package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.models.Task;
import duke.ui.Ui;

/**
 * Handles the Delete command of the user to delete a certain task in the list.
 * Format of command: "delete [task_index]".
 */
public class DeleteCommand implements Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task curTask = tasks.getTask(index - 1);
        tasks.removeTask(index - 1);
        ui.printIndentOutput("Nice! I've removed this task:");
        ui.printIndentOutput("   " + curTask);
        ui.printIndentOutput("Now you have " + tasks.size() + " task(s) in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
