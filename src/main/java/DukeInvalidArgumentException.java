/** Inherits DukeException to describe invalid arguments. */
public class DukeInvalidArgumentException extends DukeException {
    @Override
    public String toString() {
        return "Sorry, you have supplied invalid arguments!";
    }
}
