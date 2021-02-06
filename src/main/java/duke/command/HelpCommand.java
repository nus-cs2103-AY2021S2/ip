package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command for help input
 */
public class HelpCommand extends Command {

    /**
     * Prints help message
     * @param task
     * @param taskList
     * @param storage
     */
    @Override
    public String execute(String taskDescription, Task task, TaskList taskList, Storage storage) {
        // print custom help message
        String allCommands = "todo\n"
                + "deadline *text* /by yyyy-mm-dd\n"
                + "event *text* /at yyyy-mm-dd\n"
                + "done *number*\n"
                + "list\n"
                + "delete *number*\n"
                + "bye";
        return Ui.message(allCommands);
    }
}
