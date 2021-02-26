package duke.commands;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.exceptions.TaskNotFoundException;
import duke.exceptions.TaskSelectionInvalidException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Responsible for dealing with the deletion of tasks.
 */
public class DeleteCommand extends Command {
    private String[] checkCommands;

    /**
     * Constructs a DeleteCommand with the given full command line.
     *
     * @param fullCommand Full command line input.
     */
    public DeleteCommand(String fullCommand) {
        assert fullCommand.length() > 0;

        this.checkCommands = fullCommand.split(" ");
    }

    /**
     * Returns a message to indicate successful task deletion.
     * Deletes the indicated task from TaskList given by command line
     * and save changes into the save file.
     *
     * @param tasks TaskList to delete task from.
     * @param ui Ui for system outputs.
     * @param storage Storage for saving contents into file.
     * @return Successful deletion of task string.
     * @throws DukeException If error happens while deleting tasks.
     * @throws IOException If error happens while saving contents into file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (checkCommands.length == 1 || !isNumber(checkCommands[1])) {
            throw new TaskSelectionInvalidException();
        }
        int num = Integer.parseInt(checkCommands[1]);
        if (num > 0 && num <= tasks.size()) {
            Task deleted = tasks.getTask(num - 1);
            tasks.remove(num - 1);
            storage.save(tasks);
            return ui.getDeleteTaskString(tasks, deleted);
        } else {
            throw new TaskNotFoundException();
        }
    }

    private boolean isNumber(String checkString) {
        try {
            Integer.parseInt(checkString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
