package duke;

public class DukeException extends Exception {
    private static final String TOP_BORDER = "_____________________________________________________________________";
    private static final String BTM_BORDER = "---------------------------------------------------------------------";
    private static final String PADDING = "  ";

    public DukeException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return TOP_BORDER + "\n" + PADDING + super.getMessage() + "\n" + BTM_BORDER;
    }
}
