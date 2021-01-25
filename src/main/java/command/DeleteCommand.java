package command;

import Exceptions.DukeException;
import Exceptions.IncorrectNumberException;
import ui.Ui;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;

public class DeleteCommand extends Command{
    private final int taskNum;

    public DeleteCommand(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("Please key in a task number!");
        }

        int num = Integer.parseInt(description);
        this.taskNum = num;

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getNumOfTasks() == 0) {
            throw new DukeException("There are currently no tasks in your list.");
        }

        if (taskNum < 1 || taskNum > tasks.getNumOfTasks()) {
            throw new IncorrectNumberException(this.taskNum);
        }
        Task task = tasks.getTask(taskNum);
        tasks.delete(taskNum);
        ui.displayDeletedTask(task, tasks.getNumOfTasks());
        storage.saveTasks(tasks.getTasks());
    }

    public boolean isExit() {
        return false;
    }

}
