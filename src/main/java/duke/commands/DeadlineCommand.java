package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.DukeResponses;

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
     * @param dukeResponses
     * @param storage
     * @return add deadline success message, or
     *         an error message
     */
    public String execute(TaskList tasks, DukeResponses dukeResponses, Storage storage) {
        try {
            Task deadline = new Deadline(deadlineDescription, period);
            tasks.addTask(deadline);
            storage.saveFile(tasks.getTaskList());
            return dukeResponses.showAddTask(deadline, tasks);
        } catch (DukeException e) {
            return dukeResponses.showError(e);
        }
    }
}
