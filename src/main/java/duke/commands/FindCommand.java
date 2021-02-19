package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.DukeResponses;

import java.util.ArrayList;

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
     * @param dukeResponses
     * @param storage
     * @return result of find task message
     */
    public String execute(TaskList tasks, DukeResponses dukeResponses, Storage storage) {
        ArrayList<Task> filteredTasks = tasks.findTask(keyword);
        return dukeResponses.showTaskList(filteredTasks, "matching ");
    }
}
