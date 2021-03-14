/**
 * Handles errors from Duke such as incorrect input from users.
 */
public class DukeException extends Exception{
    /**
     * Constructor for this Duke object.
     * 
     * @param   s  Error message to be displayed.
     */
    public DukeException(String s) {
        super(s);
    }
}
