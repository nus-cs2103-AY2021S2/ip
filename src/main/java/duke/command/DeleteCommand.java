package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int idx;

    /**
     * Creates a DeleteCommand acting upon task at index idx
     *
     * @param idx
     */
    public DeleteCommand(int idx) {
        super(false);
        this.idx = idx - 1;
    }

    /**
     * Deletes task at index idx from taskList
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task deleted = taskList.remove(idx - 1);
        ui.printIndented("Noted. I've removed this task:");
        ui.printIndented(String.format("  %s", deleted));
        ui.printIndented(String.format("Now you have %d tasks in the list.", taskList.size()));
        storage.write(taskList.toDataString());
    }
}
