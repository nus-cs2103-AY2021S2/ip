package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Encapsulates the information of a parsed command to mark a task as done.
 */
public class DoneCommand extends Command {
    private int taskId;

    /**
     * @param fullCommand The full user command.
     * @param taskId The task id of the task which will be marked as done.
     */
    public DoneCommand(String fullCommand, int taskId) {
        super(fullCommand);
        assert taskId > 0;
        this.taskId = taskId;
    }

    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.doneTask(taskId);
        String resp = "Nice! I've marked this task as done: \n" + "  " + task;
        storage.save(tasks);
        return resp;
    }

    public boolean isExit() {
        return false;
    }
}
