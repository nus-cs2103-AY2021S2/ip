package main.java.command;

import main.java.subfiles.TaskList;
import main.java.subfiles.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("");
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
