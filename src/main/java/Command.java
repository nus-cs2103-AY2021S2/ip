import java.io.IOException;

/**
 * Represents a possible command that users can call on the Duke bot.
 * Specific commands will inherit from this abstract class.
 */
public abstract class Command {
    private boolean isExit = false;

    /**
     * Executes the given command and modifies the TaskList and Storage.
     * Response with Ui to update user.
     * @param taskList TaskList that contains all tasks at hand.
     * @param ui Ui that deals with interaction with the user.
     * @param storage Storage that deals with storing TaskList into hard drive.
     * @throws IOException If program is unable to store data into hard drive.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws IOException;

    /**
     * To identify if a command causes the Duke bot to stop running.
     */
    public void toggleExit() {
        this.isExit = !this.isExit;
    }

    /**
     * Getter to check if a command is an exit command.
     * @return If a command is an exit command.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
