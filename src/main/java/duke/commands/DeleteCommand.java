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
        this.checkCommands = fullCommand.split(" ");
    }

    /**
     * Deletes the indicated task from TaskList given by command line
     * and save changes into the save file.
     *
     * @param tasks TaskList to delete task from.
     * @param ui Ui for system outputs.
     * @param storage Storage for saving contents into file.
     * @throws DukeException If error happens while deleting tasks.
     * @throws IOException If error happens while saving contents into file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (checkCommands.length == 1 || !isNumber(checkCommands[1])) {
            throw new TaskSelectionInvalidException();
        }
        int num = Integer.parseInt(checkCommands[1]);
        if (num > 0 && num <= tasks.size()) {
            Task deleted = tasks.getTask(num - 1);
            tasks.remove(num - 1);
            ui.showDeleteTask(tasks, deleted);
        } else {
            throw new TaskNotFoundException();
        }
        storage.save(tasks);
    }

    /**
     * Returns if program should exit after this command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
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
