package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * class FindCommand
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represent an executable command that corresponds to the user input "find"
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor: creates a new EventCommand
     * @param keyword keyword to search for matching tasks
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * execute: executes the command
     * @param tasks the list of tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.findTask(keyword);
        ui.showTaskList(filteredTasks, "matching ");
    }

    /**
     * isExit: checks if Duke should terminate
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
