package duke.command;

import duke.Storage;
import duke.task.TaskList;

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
        super.commandDetail = "";
        super.dateTime = "";
        super.outputMessage = "";
        // index is -1 because it is only used in done and delete tasks
        super.index = -1;
    }

    /**
     * Output message to the command line
     *
     * @param tasks TaskList
     * @param storage Instance of Storage
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        this.outputMessage = "Bye. Hope to see you again soon!";
    }
}
