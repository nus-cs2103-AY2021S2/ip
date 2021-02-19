package duke;

/**
 * Provides abstract class for types of commands.
 */
public abstract class Command {

    /**
     * Provides abstract method that executes command.
     *
     * @param taskList the TaskList object that contains all tasks added by user.
     * @param ui the Ui object that provides responses to the user according to status of their input.
     * @param storage the Storage object that contains the tasks saved in computer's hard disk.
     * @throws DukeException if command cannot be executed.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
