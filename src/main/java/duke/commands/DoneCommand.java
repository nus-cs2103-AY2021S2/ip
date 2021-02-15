package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.DukeResponses;

/**
 * class DoneCommand
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represent an executable command that corresponds to the user input "done"
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Constructor: creates a new DoneCommand
     * @param index position of Task in TaskList to mark as complete
     */
    public DoneCommand (int index) {
        this.index = index;
    }

    /**
     * execute: executes the command
     * @param tasks the list of tasks
     * @param dukeResponses
     * @param storage
     * @return complete task success message, or
     *         an error message
     */
    public String execute(TaskList tasks, DukeResponses dukeResponses, Storage storage) {
        try {
            Task taskToComplete = tasks.getTask(index);
            tasks.completeTask(index);
            storage.saveFile(tasks.getTaskList());
            return dukeResponses.showCompleteTask(taskToComplete, tasks);
        } catch (DukeException e) {
            return dukeResponses.showError(e);
        }
    }
}
