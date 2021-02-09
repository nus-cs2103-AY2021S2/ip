package duke.command;


import duke.ui.Ui;

/**
 * Sub-class of Command to represents any error in the instruction of user.
 */
public class ErrorCommand extends Command {
    /**
     * Create an ErrorCommand object that return error message when executed.
     *
     */
    public ErrorCommand() {
        super("", "", "", false, command -> Ui.COMMAND_ERROR);
    }
}
