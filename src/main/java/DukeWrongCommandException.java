public class DukeWrongCommandException extends DukeException {
    String command;

    public DukeWrongCommandException(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return String.format("     ☹ Sorry, '%s' is not a proper command for Duke.\n", command);
    }
}