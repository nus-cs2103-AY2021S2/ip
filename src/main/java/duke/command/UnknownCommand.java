package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

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
     * @param storage  The storage handler.
     * @return Output for GUI.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "       OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
