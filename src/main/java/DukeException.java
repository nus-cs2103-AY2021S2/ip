/**
 * Handles errors from Duke such as incorrect input from users.
 */
public class DukeException extends Exception{
    public DukeException(String s) {
        super(s);
    }
}
