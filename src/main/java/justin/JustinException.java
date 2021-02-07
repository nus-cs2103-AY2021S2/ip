package justin;

/**
 * This class creates the exception message
 * for the JustionException class
 *
 *
 * @author Goh Wei Kiat aka github : mrweikiat
 * @version CS2103T AY20/21 Semester 2, Individual Project 'IP'
 */

public class JustinException extends Exception {
    /**
     * This method returns the message
     * intended for the Exception
     *
     * @param message The intented exception message
     */

    public JustinException(String message) {
        super(message);
    }
}