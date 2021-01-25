package main.java.command;

import main.java.exceptions.DateFormatException;
import main.java.exceptions.EmptyDescriptionException;
import main.java.exceptions.EmptyTimeException;
import main.java.exceptions.InvalidInputException;

import main.java.subfiles.TaskList;
import main.java.subfiles.Ui;

public class AddCommand extends Command {

    public AddCommand(String command) {
        super(command);
    }

    /**
     * Calls the task manager to add a specified task to the
     * list upon receiving a user input that attempts to add
     * a task to the list.
     *
     * @param taskList
     * @param ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            taskList.addTask(command);
        } catch (EmptyDescriptionException | EmptyTimeException | InvalidInputException |
                DateFormatException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
