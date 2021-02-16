package tlylt.haha;

/**
 * Representation of wrong task format command error.
 */
public class HahaWrongTaskFormatException extends HahaException {
    HahaWrongTaskFormatException(String command) {
        super(command);
    }

    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, "
                + "please follow the format strictly :-(\n"
                + "deadline homework /by 2020-02-02 18:00\n"
                + "event CNY /at 2020-02-15 16:00\n";
    }
}
