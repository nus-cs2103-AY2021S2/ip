/**
 * Represents the Command for "list" for the Henchman object. CommandList helps list and return all the tasks in tasks.
 */
public class CommandList extends Command {

    /**
     * Lists all tasks in TaskList.
     *
     * @param tasks TaskList of Henchman object.
     * @param storage Storage of Henchman object.
     * @return Return message containing all tasks in tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return this.toHenchmanOutput() + "\n" + tasks.printTasks();

    }

    /**
     * Returns the string representation of the message to be printed.
     *
     * @return String representation of the message to be printed.
     */
    @Override
    public String toHenchmanOutput() {
        return "Aye boss, here to see your \"collection\" eh?";
    }
}
