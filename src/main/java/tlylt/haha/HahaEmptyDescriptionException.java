package tlylt.haha;

/**
 * Representation of no required description error.
 */
public class HahaEmptyDescriptionException extends HahaException {
    HahaEmptyDescriptionException(String command) {
        super(command);
    }

    @Override
    public String toString() {
        return "OOPS!!! The description of a " + getCommand()
                + " cannot be empty.";
    }
}
