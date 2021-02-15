package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Encapsulates the information of a parsed user command to delete a task.
 */
public class DeleteCommand extends Command {
    private int taskId;

    /**
     * @param fullCommand The full user command.
     * @param taskId The task id of the task which will be deleted.
     */
    public DeleteCommand(String fullCommand, int taskId) {
        super(fullCommand);
        this.taskId = taskId;
    }

    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(taskId);
        String resp = "Noted. I've removed this task: \n" + "  " + task +
                "\nNow you have " + tasks.getNumOfTasks() + " tasks in the list.";
        storage.save(tasks);
        return resp;
    }

    public boolean isExit() {
        return false;
    }
}
