package duke.exception;

/**
 * A CommandException happens when a command is not understood or is asked to do something erroneous
 */
public class CommandException extends DukeException {

    /**
     * constructs a new command exception;
     * @param str an error message
     */
    public CommandException(String str) {
        super(str);
    }

}
