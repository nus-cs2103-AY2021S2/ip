package duke;

public class DukeMissingDescriptionException extends DukeException {
    private String command;

    public DukeMissingDescriptionException(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return String.format("     Sorry, '%s' task has missing description ☹\n", command);
    }
}
