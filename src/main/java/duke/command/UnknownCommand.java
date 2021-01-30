package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A UnknownCommand class to handle unknown commands given by user.
 */
public class UnknownCommand extends Command {
    /**
     * Constructs UnknownCommand.
     */
    public UnknownCommand() {
        super();
    }

    /**
     * Prints error message when unknown command encountered.
     *
     * @param taskList The list of tasks.
     * @param ui       The user interface.
     * @param storage  The storage handler.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("       OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Program does not exit.
     *
     * @return False to continue the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
