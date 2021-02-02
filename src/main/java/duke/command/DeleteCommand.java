package duke.command;

import duke.DukeException;
import duke.Storage;
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
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        Task deleted = taskList.remove(idx - 1);
        storage.write(taskList.toDataString());
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                deleted, taskList.size());
    }
}
