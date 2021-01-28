package duke.command;


import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents a command that makes the chat bot exit.
 */
public class ExitCommand extends Command{
    /**
     * Executes the command.
     * @param tasks The task list used for execution of the command.
     * @param ui Interactions with users.
     * @param storage Data stored in the local file path.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayBye();
    }

    /**
     * Returns a boolean value to signal the chat bot to exit.
     * @return True as the command signals the bot to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
