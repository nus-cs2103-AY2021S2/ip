package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.IncorrectNumberException;
import duke.tasks.TaskList;

public class DoneCommand extends Command {
    private final int taskNum;

    public DoneCommand(String descriptionString) throws DukeException {
        if (descriptionString.isBlank()) {
            throw new DukeException("Please key in a task number!");
        }

        this.taskNum = Integer.parseInt(descriptionString);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getNumOfTasks() == 0) {
            throw new DukeException("There are currently no tasks in your list.");
        }

        if (taskNum < 1 || taskNum > tasks.getNumOfTasks()) {
            throw new IncorrectNumberException(taskNum);
        }

        tasks.markAsDone(taskNum);
        String result = ui.displayDoneTask(tasks.getTask(taskNum));
        storage.saveTasks(tasks.getTasks());
        return result;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
