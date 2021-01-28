package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.models.Task;

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
