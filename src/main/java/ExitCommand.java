/**
 * Encapsulates the information of an parsed exit command.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super("bye");
    }

    /**
     * Executes the user command to exit Duke.
     * @param tasks A TaskList object which encapsulates the data and operations on a task list.
     * @param ui A Ui object which deals with interactions with the user.
     * @param storage A Storage object which deals with loading tasks from the file and saving tasks in the file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayBye();
    }

    public boolean isExit() {
        return true;
    }
}
