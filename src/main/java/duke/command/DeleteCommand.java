package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        super(false);
        this.idx = idx - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task deleted = taskList.remove(idx - 1);
        ui.printIndented("Noted. I've removed this task:");
        ui.printIndented(String.format("    %s", deleted));
        ui.printIndented(String.format("Now you have %d tasks in the list.", taskList.size()));
        storage.write(taskList.toDataString());
    }
}