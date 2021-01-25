package main.java.duke.command;

import main.java.duke.exceptions.InvalidInputException;
import main.java.duke.exceptions.ListOutOfBoundsException;
import main.java.duke.subfiles.TaskList;
import main.java.duke.subfiles.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        super(command);
    }

    /**
     * Calls the task manager to delete a specified task from
     * the list upon receiving a user input that attempts to
     * delete a task from the list.
     *
     * @param taskList
     * @param ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            taskList.deleteTask(command);
        } catch (InvalidInputException | ListOutOfBoundsException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
