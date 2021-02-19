/**
 * Represents the Command for "find" for the Henchman object. CommandFind helps find mark the task specified by
 * the search term, and returns the list of task containing said term.
 */
public class CommandFind extends Command {
    private final String QUERY;

    public CommandFind(String query) {
        this.QUERY = query;
    }

    /**
     * find mark the task specified by query, and returns the list of task containing said term.
     *
     * @param tasks TaskList of Henchman object.
     * @param storage Storage of Henchman object
     * @return Return message containing all found tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return this.toHenchmanOutput() + "\n" + tasks.findTasks(QUERY);
    }

    /**
     * Returns the string representation of the message to be printed.
     *
     * @return String representation of the message to be printed.
     */
    @Override
    public String toHenchmanOutput() {
        return "Sure thing boss, I'll find them in a jiffy.";
    }
}
