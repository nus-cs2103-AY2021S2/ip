package duke.command;

import duke.Ui;

public class ExitCommand implements ICommand {
    private Ui ui;

    /**
     * Constructor method for ExitCommand
     * @param tasks
     */
    public ExitCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public void execute(String parameters) {
        ui.createDukeDialog("Bye. Hope to see you again soon!");
        ui.handleExit();
    }
}
