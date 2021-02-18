/**
 * Represents the Command for "bye" for the Duke object. CommandBye helps to close and prepare Duke object for ending
 * the run.
 */
public class CommandBye extends Command {
    /**
     * Closes the provided attributes of Duke object in preparation of ending the run.
     *
     * @param tasks TaskList of Duke object
     * @param storage Storage of Duke object
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        // TODO INITIATE SHUTDOWN IN ALL ARGUMENTS
        return this.toDukeOutput();
    }

    /**
     * Returns the string representation of the message to be printed by the Duke object's Ui.
     *
     * @return String representation of the message to be printed by the Duke object's Ui.
     */
    @Override
    public String toDukeOutput() {
        return "I see you're done for now boss, see you soon!";
    }
}
