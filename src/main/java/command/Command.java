package main.java.command;

import main.java.subfiles.TaskList;
import main.java.subfiles.Ui;

public abstract class Command {
    protected String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract void execute(TaskList taskList, Ui ui);

    public abstract boolean isExit();

}
