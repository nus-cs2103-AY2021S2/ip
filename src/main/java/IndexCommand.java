public abstract class IndexCommand extends Command {
    protected String fullCommand;

    IndexCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public int getIndex() throws DescriptionMissingException {
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

    @Override
    public boolean isExit() {
        return false;
    }
}
