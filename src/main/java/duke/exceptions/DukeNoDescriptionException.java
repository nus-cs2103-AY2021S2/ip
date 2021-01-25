package duke.exceptions;

public class DukeNoDescriptionException extends DukeException {
    private final String command;

    public DukeNoDescriptionException(String command) {
        this.command = command;
    }

    public String toString() {
        return String.format("☹ OOPS!!! The description of a %s cannot be empty.", command);
    }
}
