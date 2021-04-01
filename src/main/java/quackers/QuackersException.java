package quackers;

/**
 * Represents the custom exception used by Quackers.
 */
public class QuackersException extends Exception {

    /**
     * Constructs a QuackersException object.
     *
     * @param message Exception message.
     */
    public QuackersException(String message) {
        super(message);
    }
}
