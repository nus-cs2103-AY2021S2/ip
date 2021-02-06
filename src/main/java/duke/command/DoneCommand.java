package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

public class DoneCommand extends Command {

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
     * @throws DukeException If task number does not exist or is not specified.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (Integer.parseInt(this.description) > tasks.getTaskListSize()) {
            throw new DukeException("☹ OOPS!!! This task number does not exist.");
        }
        int taskNo = Integer.parseInt(this.description);
        tasks.getTask(taskNo).markAsDone();
    }

    @Override
    public boolean isExit() {
        return this.isExit;
    }
}
