package duke.command;

import duke.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;


/**
 * DoneCommand represent a command to mark a task as done.
 */
class DoneCommand extends Command {

    /**
     * Returns a DoneCommand object to mark the given task as done.
     *
     * @param index The index of the task to be mark as done.
     */
    DoneCommand(String index) {
        super(null, index, null, null, false);
    }

    /**
     * Marks the task with the index as done. Then, outputs a message when the
     * previous action is successfully done. Save the current list to the data file.
     *
     * @param tasks TaskList storing the current tasks.
     * @param ui The user interface of the program.
     * @param storage The storage of the program.
     * @throws DukeException If an I/O error occurs or the index if out of bounds of the list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.markTaskAsDone(description);
        ui.showDone(t.toString(), tasks.getSize());
        storage.save(tasks.listOutTaskInString());
    }
}
