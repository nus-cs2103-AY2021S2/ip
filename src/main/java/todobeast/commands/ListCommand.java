package todobeast.commands;

import todobeast.Storage;
import todobeast.TaskList;
import todobeast.Ui;
import todobeast.exceptions.ToDoBeastException;

public class ListCommand extends Command {

    public void execute(TaskList taskList, Ui ui) throws ToDoBeastException {
        if (taskList == null) {
            throw new ToDoBeastException("Task list cannot be found.");
        }
        ui.printTaskList(taskList);
    }
}
