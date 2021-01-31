package duke.command;

import duke.Storage;
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

    /**
     * Outputs response to terminal
     *
     * @param tasks TaskList
     * @param storage Storage instance
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        output = "Bye. Hope to see you again soon!";
    }

    /**
     * Determines if Exit is called by user
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
