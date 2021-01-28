package duke.command;

import duke.exception.DescriptionMissingException;

/**
 * An abstract class represents an IndexCommand.
 */
public abstract class IndexCommand extends Command {
    protected String fullCommand;

    /**
     * Constructs an IndexCommand.
     * @param fullCommand The full command from the user's input.
     */
    public IndexCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Returns the index specified in the user's input.
     * @return Index from the user's input.
     * @throws DescriptionMissingException If the input contains no number.
     */
    protected int getIndex() throws DescriptionMissingException {
        String[] doneInstructions = fullCommand.strip().split(" ");

        if (doneInstructions.length != 2) {
            throw new DescriptionMissingException("Argument missing!");
        } else {
            try {
                String indexString = doneInstructions[1].strip();
                return Integer.parseInt(indexString) - 1;
            } catch (NumberFormatException e) {
                throw new DescriptionMissingException("Invalid argument!");
            }
        }
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
