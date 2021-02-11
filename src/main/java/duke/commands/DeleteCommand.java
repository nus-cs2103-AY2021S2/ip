package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

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
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task taskToDelete = tasks.getTask(index);
            tasks.deleteTask(index);
            ui.showDeleteTask(taskToDelete, tasks);
            storage.saveFile(tasks.getTaskList());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * isExit: checks if Duke should terminate
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
