package duke.command;

import duke.Storage;
import duke.Ui;
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
     * @param ui Ui instance
     * @param storage Storage instance
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        output = "Bye. Hope to see you again soon!";
        ui.response(output);
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
