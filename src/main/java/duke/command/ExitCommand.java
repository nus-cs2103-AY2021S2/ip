package main.java.duke.command;

import main.java.duke.subfiles.TaskList;
import main.java.duke.subfiles.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("");
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
