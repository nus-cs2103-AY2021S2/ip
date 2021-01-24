/**
 * Represents a command that adds tasks.
 */
abstract class AddCommand implements Command {

    protected String commandType;
    protected String description;
    protected Task newTask;
    protected int numTasks;

    /**
     * Constructor for AddCommand class command name and description.
     * @param command Name of the command.
     * @param description Description of the command.
     */
    public AddCommand(String commandType, String description) {
        this.commandType = commandType;
        this.description = description;
    }

    /**
     * Returns positive if command terminates chat bot.
     * @return True if this command terminates chat bot.
     */
    public boolean shouldExit() {
        return false;
    }

    public abstract TaskList execute(TaskList taskList);

    public String getResponse() {
        return "Got it. I've added this task:\n  ";
    }

}
