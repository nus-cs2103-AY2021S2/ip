package exception;

public class MikeInvalidInputException extends Exception {
    /**
     * Execution to be thrown when user input is detected as invalid
     */
    public MikeInvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}
