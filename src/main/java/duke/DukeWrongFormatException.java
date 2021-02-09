package duke;

public class DukeWrongFormatException extends DukeException {
    private String command;

    DukeWrongFormatException(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        String formatMsg = String.format("      '%s' command has wrong format!\n", command);
        return formatMsg + HELP_MSG;
    }
}
