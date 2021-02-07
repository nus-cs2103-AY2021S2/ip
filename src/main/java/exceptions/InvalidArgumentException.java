package exceptions;

public class InvalidArgumentException extends Exception {
    /**
     * Invalid argument exception for e.g. when an unapplicable command is used on an empty list
     * @param errMsg Error message to display
     */
    public InvalidArgumentException(String errMsg) {
        super(errMsg);
    }
}
