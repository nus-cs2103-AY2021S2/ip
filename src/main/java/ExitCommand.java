/**
 * Represents a command involving the termination of the program.
 */
public class ExitCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean isExit() {
        return true;
    }

    /**
     * Checks the equivalence of ExitCommand this and Object obj.
     * If obj is an instance of the ExitCommand class and all attributes are equivalent,
     * it is equivalent to this.
     * @param obj the object which will be compared to this.
     * @return Indication of whether obj is equivalent to this.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof ExitCommand) {
            return true;
        }
        return false;
    }
}
