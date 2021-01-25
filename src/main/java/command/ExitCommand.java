package main.java.command;

import main.java.TaskManager;
import main.java.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();

    }

    @Override
    public void execute(TaskManager tm, Ui ui) {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
