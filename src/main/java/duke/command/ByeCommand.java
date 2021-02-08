package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Handles user request to exit program
 */
public class ByeCommand extends Command {

    /**
     * Constructor for DeleteCommand
     *
     * @param command Bye command
     */
    public ByeCommand(String command) {
        super.command = command;
        super.description = "";
        super.date = "";
    }

    @Override
    protected void updateOutput(Task task, TaskList tasks) {
        output = "Bye. Hope to see you again soon!";
    }

    /**
     * Outputs response to terminal
     *
     * @param tasks TaskList
     * @param storage Storage instance
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        updateOutput(null, tasks);
    }

}
