package duke;

public class ByeCommand extends Command {
    /**
     * Prints out bye message.
     * @param tl Task list of Duke.
     * @param ui User interface for Duke.
     * @param storage Storage to save files.
     * @return String message for bye.
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        return ui.printBye();
    }
}
