package helper;

/**
 * Exception class for duke
 */
public class DukeException extends Exception {

    private String error;

    /**
     * Make duke exception
     * @param errorMessage
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        error = errorMessage;
    }

    /**
     * Show the error
     * @return String error
     */
    public String showError() {
        return error;
    }
}
