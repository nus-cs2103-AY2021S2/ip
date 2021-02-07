package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;

/**
 * Represents a list command keyed in by the user.
 */
public class ListCommand extends Command {

    public ListCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    /**
     * Executes the list command by printing all the tasks to the user.
     *
     * @param taskManager TaskManager object that maintains the list of tasks.
     * @param ui Ui object that handles user interaction.
     * @param storage Storage object that handles the updating of stored file.
     * @return String string to output to user.
     */
    public String execute(TaskManager taskManager, Ui ui, Storage storage) {
        return ui.showAllTasks(taskManager.getList());
    }
}
