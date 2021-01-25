package main.java.command;

import main.java.exceptions.InvalidInputException;
import main.java.exceptions.ListOutOfBoundsException;
import main.java.subfiles.TaskList;
import main.java.subfiles.Ui;

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
