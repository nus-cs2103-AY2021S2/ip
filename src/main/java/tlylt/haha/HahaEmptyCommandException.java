package tlylt.haha;

/**
 * Representation of empty command error.
 */
public class HahaEmptyCommandException extends HahaException {
    HahaEmptyCommandException(String command) {
        super(command);
    }

    @Override
    public String toString() {
        return "Empty Command! Please enter something legit.";
    }
}
