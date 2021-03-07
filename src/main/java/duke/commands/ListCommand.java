package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.DukeResponses;

import java.util.ArrayList;

/**
 * class ListCommand
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represent an executable command that corresponds to the user input "list"
 */
public class ListCommand extends Command {
    /**
     * execute: executes the command
     * @param tasks the list of tasks
     * @param dukeResponses
     * @param storage
     * @return list of tasks
     */
    public String execute(TaskList tasks, DukeResponses dukeResponses, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        return dukeResponses.showTaskList(taskList, "");
    }
}
