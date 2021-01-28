/**
 * Command to stop the Duke bot.
 * Inherits from the superclass Command.
 */
public class ExitCommand extends Command {
    /**
     * Mark this command as an exit command.
     * @param taskList TaskList that contains all tasks at hand.
     * @param ui Ui that deals with interaction with the user.
     * @param storage Storage that deals with storing TaskList into hard drive.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        super.toggleExit();
    }
}
