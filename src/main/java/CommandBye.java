/**
 * Represents the Command for "bye" for the Henchman object. CommandBye helps to close and prepare Duke object for
 * ending the run.
 */
public class CommandBye extends Command {
    /**
     * Prints bye message.
     *
     * @param tasks TaskList of Henchman object
     * @param storage Storage of Henchman object
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return this.toHenchmanOutput();
    }

    /**
     * Returns the string representation of the message to be printed.
     *
     * @return String representation of the message to be printed.
     */
    @Override
    public String toHenchmanOutput() {
        return "I see you're done for now boss, see you soon!";
    }
}
