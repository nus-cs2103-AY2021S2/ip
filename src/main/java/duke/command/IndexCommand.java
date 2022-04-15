package duke.command;

/**
 * An abstract class represents an IndexCommand.
 */
public abstract class IndexCommand extends Command {
    protected int index;

    /**
     * Constructs an IndexCommand.
     * @param index The index of the task specified.
     */
    public IndexCommand(int index) {
        this.index = index;
    }

    /**
     * Returns false because this is not an ExitCommand.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
