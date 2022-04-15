package jeff;

/**
 * Represents any errors thrown during execution of Jeff.
 */
public class JeffException extends Exception {

    /**
     * Constructor for JeffException.
     *
     * @param errorMessage Message describing error.
     */
    public JeffException(String errorMessage) {
        super ("Oops! Error: " + errorMessage);
    }
}
