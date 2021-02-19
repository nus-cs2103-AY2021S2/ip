package duke.command;

import duke.FileManager;
import duke.Storage;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to mark a task as done.
 */
public class DoneCommand extends Command {

    /**
     * Creates a new instance of <code>DoneCommand</code>.
     *
     * @param description Task number to be marked as done.
     */
    public DoneCommand(String description) {
        this.type = "done";
        this.description = description;
        this.isExit = false;
    }

    /**
     * Marks tasks as combine.
     *
     * @param tasks Task list.
     * @param ui User interface.
     * @param storage Storage.
     * @return Output string.
     * @throws DukeException If task number does not exist or is not specified.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (Integer.parseInt(this.description) > tasks.getTaskListSize() || Integer.parseInt(this.description) == 0) {
            throw new DukeException("☹ OOPS!!! This task number does not exist.");
        }
        int taskNo = Integer.parseInt(this.description);
        assert (taskNo > 0 && taskNo < tasks.getTaskListSize()) : "Invalid task number to be marked as done.";
        String doneTask = tasks.getTask(taskNo).markAsDone();
        FileManager.updateTaskList(storage.getFilePath(), tasks);
        return doneTask;
    }

    @Override
    public boolean isExit() {
        return this.isExit;
    }
}
