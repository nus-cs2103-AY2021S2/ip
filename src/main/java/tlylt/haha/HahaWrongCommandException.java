package tlylt.haha;

/**
 * Representation of wrong command error.
 */
public class HahaWrongCommandException extends HahaException {
    HahaWrongCommandException(String command) {
        super(command);
    }

    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, "
                + "but I don't know what that means :-(\n"
                + "Are you sure you mean the following?\n"
                + "\"" + getCommand() + "\"";
    }
}
