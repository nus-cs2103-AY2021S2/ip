package duke.command;

import duke.exception.DescriptionMissingException;

/**
 * An abstract class represents an IndexCommand.
 */
public abstract class IndexCommand extends Command {
    protected int index;

    /**
     * Constructs an IndexCommand.
     * @param index The full command from the user's input.
     */
    public IndexCommand(int index) {
        this.index = index;
    }

    /**
     * Returns false.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
