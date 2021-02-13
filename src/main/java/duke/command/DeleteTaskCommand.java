package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteTaskCommand extends Command {

    private static final String deleteSuccess = "Deleted task.";
    private static final Boolean toExit = false;

    private Integer taskIndex;

    /**
     * Initialises delete task command by specifying
     * the task index to delete.
     *
     * @param taskIndex Task index.
     */
    public DeleteTaskCommand(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes specific task from the task list.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     * @param storage Core Storage object.
     * @return Command execution response.
     * @throws DukeException If invalid task index specified.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.deleteTask(this.taskIndex);
        return new CommandResponse(DeleteTaskCommand.deleteSuccess, DeleteTaskCommand.toExit);
    }
}
