package project.command;

import project.io.Ui;
import project.storage.Storage;
import project.task.TaskList;

public abstract class Command {
    protected String userInput;

    public Command() {
        this.userInput = "";
    }

    public Command(String userInput) {
        this.userInput = userInput;
    }
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}
