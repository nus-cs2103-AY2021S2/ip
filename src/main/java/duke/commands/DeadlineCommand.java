package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * class DeadlineCommand
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represent an executable command that corresponds to the user input "deadline"
 */
public class DeadlineCommand extends Command{
    private String deadlineDescription;
    private String period;

    /**
     * Constructor: creates a new DeadlineCommand
     * @param deadlineDescription description of Deadline
     * @param period period of Deadline
     */
    public DeadlineCommand(String deadlineDescription, String period) {
        this.deadlineDescription = deadlineDescription;
        this.period = period;
    }

    /**
     * execute: executes the command
     * @param tasks the list of tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task deadline = new Deadline(deadlineDescription, period);
            tasks.addTask(deadline);
            ui.showAddTask(deadline, tasks);
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
