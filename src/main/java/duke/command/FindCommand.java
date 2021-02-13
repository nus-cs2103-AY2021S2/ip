package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.task.Task;


public class FindCommand extends Command {

    public FindCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    /**
     * Executes the find command by return a String of all the tasks containing specified keyword.
     *
     * @param taskManager TaskManager object that maintains the list of tasks.
     * @param ui Ui object that handles user interaction.
     * @param storage Storage object that handles the updating of stored file.
     * @return String string containing all matching tasks.
     */
    public String execute(TaskManager taskManager, Ui ui, Storage storage) {
        String keyword = parsedCommand[1];
        ArrayList<Task> list = taskManager.retrieveMatchingTasks(keyword);
        return ui.showMatchingTasks(list);
    }
}
