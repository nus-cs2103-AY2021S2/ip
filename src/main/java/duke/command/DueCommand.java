package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.task.Task;


/**
 * Represents a due command keyed in by the user.
 */
public class DueCommand extends Command {

    public DueCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    /**
     * Executes the due command by printing tasks that specifically end on a specified date.
     *
     * @param taskManager TaskManager object that maintains the list of tasks.
     * @param ui Ui object that handles user interaction.
     * @param storage Storage object that handles the updating of stored file.
     * @return String string to output to user.
     */
    public String execute(TaskManager taskManager, Ui ui, Storage storage) {
        String date = parsedCommand[1];
        ArrayList<Task> list = taskManager.getTasksOn(date);
        return ui.showDueTasks(list, date);
    }
}
