package duke;

public class HelpCommand extends Command{
    /**
     * Prints help message.
     * @param tl Task list of Duke.
     * @param ui User interface of Duke.
     * @param storage Storage to save list to drive.
     * @return String format of help message.
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        return ui.printHelp();
    }
}
