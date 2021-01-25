package main.java.duke.command;

import main.java.duke.exceptions.InvalidInputException;
import main.java.duke.exceptions.ListOutOfBoundsException;
import main.java.duke.subfiles.TaskList;
import main.java.duke.subfiles.Ui;

public class DoneCommand extends Command {

    public DoneCommand(String command) {
        super(command);
    }

    /**
     * Calls the task manager to mark a specified task as done
     * upon receiving a user input that attempts to mark a task
     * as done.
     *
     * @param taskList
     * @param ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            taskList.markDone(command);
        } catch (InvalidInputException | ListOutOfBoundsException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
