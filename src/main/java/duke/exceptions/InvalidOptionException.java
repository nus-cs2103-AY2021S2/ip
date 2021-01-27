package duke.exceptions;

public class InvalidOptionException extends DukeException {

    private String message;
    private final String INDENTATION = "    ";

    public InvalidOptionException(String s) {
        super();

        switch (s) {
        case "TODO":
            message = "The description of a todo cannot be empty. Please try again!";
            break;
        case "DEADLINE":
            message = "You have entered an invalid description for deadline.\n "
                    + INDENTATION
                    + "Did you specify /by? Please try again!";
            break;
        case "EVENT":
            message = "You have entered an invalid description for event.\n "
                    + INDENTATION
                    + "Did you specify /at? Please try again!";
            break;
        case "DONE":
            message = "You have entered an invalid option for done.\n "
                    + INDENTATION
                    + "Did you specify a valid number? Please try again!";
            break;
        case "DELETE":
            message = "You have entered an invalid option for delete.\n "
                    + INDENTATION
                    + "Did you specify a valid number? Please try again!";
            break;
        default:
            message = "You have entered an invalid option. Please try again!";
            break;
        }
    }

    @Override
    public String getMessage() {
        return message;
    }
}
