package duke.command;

import duke.exception.DescriptionMissingException;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class represents a FindCommand.
 */
public class FindCommand extends Command {
    protected String fullCommand;

    /**
     * Constructs a FindCommand.
     * @param fullCommand The full command from user's input.
     */
    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Collects all the matching tasks and calls ui to print therm.
     * @param tasks Task list given.
     * @param ui User interface class object.
     * @param storage Storage path that is going to be updated.
     * @throws DukeException If error occurs during the process.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String keyword = fullCommand.substring(4).strip();
        if (keyword.equals("")) {
            throw new DescriptionMissingException("Argument missing!");
        }
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task thisTask = tasks.get(i);
            String thisName = thisTask.getName();
            if (thisName.contains(keyword)) {
                matchingTasks.add(thisTask);
            }
        }
        ui.listMatchingTasks(matchingTasks);
        return ui.listMatchingTasksResponse(matchingTasks);
    }

    /**
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
