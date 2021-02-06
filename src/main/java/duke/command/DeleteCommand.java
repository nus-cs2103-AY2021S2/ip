package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.IncorrectNumberException;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DeleteCommand extends Command {
    private final int taskNum;

    public DeleteCommand(String descriptionString) throws DukeException {
        if (descriptionString.isBlank()) {
            throw new DukeException("Please key in a task number!");
        }

        int num = Integer.parseInt(descriptionString);
        this.taskNum = num;

    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getNumOfTasks() == 0) {
            throw new DukeException("There are currently no tasks in your list.");
        }

        if (taskNum < 1 || taskNum > tasks.getNumOfTasks()) {
            throw new IncorrectNumberException(this.taskNum);
        }

        Task task = tasks.getTask(taskNum);
        tasks.delete(taskNum);
        String result = ui.displayDeletedTask(task, tasks.getNumOfTasks());
        storage.saveTasks(tasks.getTasks());
        return result;
    }

    public boolean isExit() {
        return false;
    }

}
