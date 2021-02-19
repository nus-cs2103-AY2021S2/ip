package chandler;

/**
 *  ChandlerException class encapsulates information about a custom exception for Chandler.
 *  and inherits functionality from the Exception class.
 */
public class ChandlerException extends Exception {
    /**
     * Create a new ChandlerException to be thrown.
     *
     * @param msg The error message.
     */
    public ChandlerException(String msg) {
        super(msg);
    }

    /**
     * Returns a string representation of the ChandlerException.
     * @return Error message in chat box.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
