package duke.command;

import duke.Exceptions.DukeException;
import duke.Exceptions.IncorrectNumberException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private final int taskNum;

    public DoneCommand(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("Please key in a task number!");
        }

        this.taskNum = Integer.parseInt(description);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getNumOfTasks() == 0) {
            throw new DukeException("There are currently no duke.tasks in your list.");
        }

        if (taskNum < 1 || taskNum > tasks.getNumOfTasks()) {
            throw new IncorrectNumberException(taskNum);
        }

        tasks.markAsDone(taskNum);
        ui.displayDoneTask(tasks.getTask(taskNum));
        storage.saveTasks(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
