package duke.command;

import duke.Ui;
/**
 * Default Command which will get executed when keyword supplied does not match with keywords in CommandMap.
 */
public class DefaultCommand implements ICommand {
    private Ui ui;

    public DefaultCommand(Ui ui) {
        this.ui = ui;
    }
    /**
     * When executed, will print a default statement. Input given will not change
     * how function gets executed.
     *
     * @param parameters
     */
    @Override
    public void execute(String parameters) {
        ui.handleError("Error: Unknown command word. Please try again.");
    }
}
