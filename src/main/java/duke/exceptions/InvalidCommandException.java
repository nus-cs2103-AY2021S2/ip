package duke.exceptions;

/**
 * class InvalidCommandException
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represents a DukeException when the user inputs a command not recognised by Duke
 */
public class InvalidCommandException extends DukeException {
    private String errorMessage;

    /**
     * Constructor: creates a new InvalidCommandException
     */
    public InvalidCommandException () {
        super("");
        errorMessage = "I'm sorry, but I don't know what that means :-(\n" +
                "    Type <help> for a list of commands that I recognise!";
    }

    /**
     * getMessage: retrieves error message
     * @return errorMessage
     */
    @Override
    public String getMessage() {
        String updatedErrorMessage = super.getMessage() + this.errorMessage;
        return updatedErrorMessage;
    }
}
