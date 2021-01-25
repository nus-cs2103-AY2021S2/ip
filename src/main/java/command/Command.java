package main.java.command;

import main.java.TaskManager;
import main.java.Ui;

public abstract class Command {
    String commandLine;

    public Command() {

    }
    public Command(String commandLine) {
        this.commandLine = commandLine;
    }

    public abstract void execute(TaskManager tm, Ui ui);

    public boolean isExit() {
        return false;
    }
}
