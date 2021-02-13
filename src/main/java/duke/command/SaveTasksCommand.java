package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class SaveTasksCommand extends Command {

    private static final String saveSuccess = "Tasks saved to disk.";
    private static final Boolean toExit = false;

    /**
     * Saves tasks from the task list onto the disk.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     * @param storage Core Storage object.
     * @return Command execution response.
     * @throws DukeException If data cannot be saved onto the disk.
     */
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks.getList());
        return new CommandResponse(SaveTasksCommand.saveSuccess, SaveTasksCommand.toExit);
    }
}
