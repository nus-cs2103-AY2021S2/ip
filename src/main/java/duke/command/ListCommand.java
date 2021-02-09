package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.TaskList;

/**
 * Represents a command telling duke to list all current tasks
 */
public class ListCommand implements Command{

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(tasks.toString());
    }
}
