package main.java.command;

import main.java.exceptions.DateFormatException;
import main.java.subfiles.TaskList;
import main.java.subfiles.Ui;

public class PrintCommand extends Command {

    public PrintCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        String[] sArray = command.split(" ");

        if (sArray.length == 1) {
            taskList.printTasks();
        } else {
            try {
                taskList.printTasksOnDate(sArray[1]);
            } catch (DateFormatException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
