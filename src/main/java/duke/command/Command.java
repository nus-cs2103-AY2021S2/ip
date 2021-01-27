package duke.command;

import duke.Ui;
import duke.TaskManager;
import duke.exception.DukeCommandException;

/** An executable comment issued by the user */
public abstract class Command {
    /** An Ui instance that all commands will act upon */
    protected static Ui ui;
    /** A TaskManager instance that all commands will act upon */
    protected static TaskManager taskManager;

    /**
     * Setups the Ui and TaskManager instance
     *
     * @param ui Ui instance to be shared
     * @param taskManager TaskManager instance to be shared
     */
    public static void setup(Ui ui, TaskManager taskManager) {
        Command.ui = ui;
        Command.taskManager = taskManager;
    }

    /**
     * Executes the command
     *
     * @throws DukeCommandException if any issue happened during the execution of the command
     */
    public abstract void execute() throws DukeCommandException;

    /**
     * Returns a boolean to indicate whether this command will terminate the chat bot
     *
     * @return A boolean whether this command terminates the chat bot
     */
    public boolean isToExit() {
        return false;
    }
}
