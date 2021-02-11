package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

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
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task taskToComplete = tasks.getTask(index);
            tasks.completeTask(index);
            ui.showCompleteTask(taskToComplete, tasks);
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
