package duke.commands;

import duke.exception.DukeException;
import duke.message.Messages;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a user command to mark a task as done in the task list.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private int index;

    /**
     * Constructor takes in the index of the task to be marked as done in the task list.
     * @param index The 0-based index of the task to be marked as done.
     */
    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the user command and returns a response message
     * @param tasks The state of the current task list.
     * @param storage The storage used by the program.
     * @return Response message.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null;
        assert storage != null;

        Task doneTask = tasks.get(index);
        doneTask.setDone(true);
        storage.save(tasks);
        return Messages.getDoneTaskMessage(doneTask);
    }
}
