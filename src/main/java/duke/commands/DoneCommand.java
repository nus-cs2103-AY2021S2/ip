package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.commands.Command;
import duke.models.Task;

public class DoneCommand implements Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= tasks.size()) {
            throw new DukeException("The index of the task needs to be present in the list.");
        }
        Task curTask = tasks.getTask(index - 1);
        curTask.markAsDone();
        ui.printIndentOutput("Nice! I've marked this task as done:");
        ui.printIndentOutput("   " + curTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
