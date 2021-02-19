package duke.exception;

/**
 * Represent a invalid TypeOfTask parsed from user input.
 */
public class InvalidTypeOfTask extends DukeException {
    /**
     * Returns String that produces error exception message to user.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means :(\n";
    }
}
