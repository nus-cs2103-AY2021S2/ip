package duke.command;


/**
 * Sub-class of Command to represents any error in the instruction of user.
 */
public class ErrorCommand extends Command {
    public ErrorCommand(String error) {
        super("", "", "", false,
                command -> "I don't know what you saying");
    }
}
