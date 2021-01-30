package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {

    @Override
    public void execute(Task task, TaskList taskList, Storage storage) {
        // print custom help message
        String allCommands = "todo\n"
                + "deadline *text* /by yyyy-mm-dd\n"
                + "event *text* /at yyyy-mm-dd\n"
                + "done *number*\n"
                + "list\n"
                + "delete *number*\n"
                + "bye";
        Ui.printMessage(allCommands);
    }
}
