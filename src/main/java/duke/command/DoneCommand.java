package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

public class DoneCommand extends UndoRedoableCommand implements Command {
    private int idx;

    /**
     * Creates a DoneCommand acting upon task at index idx
     *
     * @param idx
     */
    public DoneCommand(int idx) {
        super();
        this.idx = idx - 1;
    }

    /**
     * Marks task at index idx of taskList as done
     *
     * @param taskList
     * @param storage
     * @throws DukeException
     */
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        assert idx >= 0 : "Index should be non-negative";
        taskList.markDone(idx);
        storage.write(taskList.toDataString());
        assert taskList.get(idx).isDone() : "The task should have been marked done";
        return String.format("Yay you're completed task %d!", idx + 1);
    }
}
