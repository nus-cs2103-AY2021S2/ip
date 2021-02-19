package bearbear.exceptions;

/**
 * Signals errors related to BearBear chat bot.
 */
public class BearBearException extends Exception {
    public BearBearException(String errorMessage) {
        super(errorMessage);
    }
}
