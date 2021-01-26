package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.Ui;

/**
 * ExitCommand handles the termination of the Duke Bot program
 */
public class ExitCommand extends Command {

    /**
     * ExitCommand Constructor
     *
     * @param commandType Task name
     */
    public ExitCommand(String commandType) {
        super.commandType = commandType;
        super.commandDetails = "";
        super.dateTime = "";
        super.outputMessage = "";
        // index is -1 because it is only used in done and delete tasks
        super.index = -1;
    }

    /**
     * Output message to the command line
     *
     * @param tasks TaskList
     * @param ui Instance of Ui
     * @param storage Instance of Storage
     * @throws DukeException If the input is invalid date format
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)  {
        this.outputMessage = " Bye. Hope to see you again soon!";
        ui.display(outputMessage);
    }

    /**
     * Determines if whether the Duke Bot should continue processing the user input
     *
     * @return False
     */
    @Override
    public boolean continueInput() {
        return false;
    }
}
