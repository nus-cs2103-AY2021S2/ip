package project.command;

import java.util.Arrays;

import project.io.Ui;
import project.storage.Storage;
import project.task.TaskList;

/**
 * Represents a Command the application responds to.
 */
public abstract class Command {
    protected String userInput;

    public Command() {
        this.userInput = "";
    }

    public Command(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Represents a task in the application.
     *
     * @param taskList The {@code TaskList} to be saved.
     * @param storage The {@code Storage}s to save into.
     */
    public void saveTasks(TaskList taskList, Storage[] storage) {
        // will save in every storage path provided
        Arrays.stream(storage).forEach(s -> {
            s.saveData(taskList);
            assert s.isSaved();
        });
    }
    public abstract String execute(TaskList taskList, Ui ui, Storage ... storage);
}
