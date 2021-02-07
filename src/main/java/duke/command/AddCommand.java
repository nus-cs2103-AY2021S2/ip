package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class AddCommand extends UndoRedoableCommand implements Command {
    private Task task;

    /**
     * Creates an AddCommand acting on the given task.
     * isExit is False.
     *
     * @param task
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Adds a new task to taskList
     *
     * @param taskList
     * @param storage
     * @throws DukeException
     */
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.add(task);
        storage.write(taskList.toDataString());
        return String.format("Got it. I've added this task:\n    %s\nNow you have %d tasks in the list.",
                task, taskList.size());
    }
}
