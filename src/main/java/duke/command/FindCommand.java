package main.java.duke.command;

import main.java.duke.subfiles.TaskList;
import main.java.duke.subfiles.Ui;

public class FindCommand extends Command {

    public FindCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.findTasksWithKeyword(command);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
