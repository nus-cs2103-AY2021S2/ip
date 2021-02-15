package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.DukeResponses;

/**
 * class DeleteCommand
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represent an executable command that corresponds to the user input "delete"
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor: creates a new DeleteCommand
     * @param index position of Task in TaskList to delete
     */
    public DeleteCommand (int index) {
        this.index = index;
    }

    /**
     * execute: executes the command
     * @param tasks the list of tasks
     * @param dukeResponses
     * @param storage
     * @return delete task success message, or
     *         an error message
     */
    public String execute(TaskList tasks, DukeResponses dukeResponses, Storage storage) {
        try {
            Task taskToDelete = tasks.getTask(index);
            tasks.deleteTask(index);
            storage.saveFile(tasks.getTaskList());
            return dukeResponses.showDeleteTask(taskToDelete, tasks);
        } catch (DukeException e) {
            return dukeResponses.showError(e);
        }
    }
}
