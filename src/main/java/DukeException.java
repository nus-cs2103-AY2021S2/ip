public class DukeException extends Exception {
    /**
     * Initializes a newly created DukeException object without a message.
     */
    DukeException() {
        super();
    }

    /**
     * Initializes a newly created DukeException object with a given message.
     * @param message Error message given for the exception
     */
    DukeException(String message) {
        super(message);
    }

    /**
     * Converts this object to a string that represents the error message
     * @return A string representing the error message
     */
    @Override
    public String toString() {
        return getMessage();
    }
}
