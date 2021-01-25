package main.java.duke.command;

import main.java.duke.subfiles.TaskList;
import main.java.duke.subfiles.Ui;

public abstract class Command {
    protected String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract void execute(TaskList taskList, Ui ui);

    public abstract boolean isExit();

}
