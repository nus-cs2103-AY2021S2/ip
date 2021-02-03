package duke.commands;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.exceptions.TaskNotFoundException;
import duke.exceptions.TaskSelectionInvalidException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Responsible for dealing with the completion of tasks.
 */
public class DoneCommand extends Command {
    private String[] checkCommands;

    /**
     * Constructs a DoneCommand with the given full command line.
     *
     * @param fullCommand Full command line input.
     */
    public DoneCommand(String fullCommand) {
        this.checkCommands = fullCommand.split(" ");
    }

    /**
     * Returns a message to indicate successful task completion.
     * Changes the completion status of indicated task in TaskList
     * given by command line and save changes into the save file.
     *
     * @param tasks TaskList containing the task.
     * @param ui Ui for system outputs.
     * @param storage Storage for saving contents into file.
     * @return Successful completion of task string.
     * @throws IOException If error happens while saving contents into file.
     * @throws DukeException If error happens while changing status of task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (checkCommands.length == 1 || !isNumber(checkCommands[1])) {
            throw new TaskSelectionInvalidException();
        }
        int num = Integer.parseInt(checkCommands[1]);
        if (num > 0 && num <= tasks.size()) {
            tasks.getTask(num - 1).markAsDone();
            storage.save(tasks);
            return ui.getDoneTaskString(tasks, num);
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
