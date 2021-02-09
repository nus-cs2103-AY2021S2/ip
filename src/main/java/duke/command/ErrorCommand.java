package duke.command;


import duke.ui.Ui;

/**
 * Sub-class of Command to represents any error in the instruction of user.
 */
public class ErrorCommand extends Command {
    public ErrorCommand() {
        super("", "", "", false,
                command -> Ui.COMMAND_ERROR);
    }
}
