/** Inherits DukeException to describe invalid commands. */
public class DukeInvalidCommandException extends DukeException {
    @Override
    public String toString() {
        return "Sorry, I don't understand the command!";
    }
}
