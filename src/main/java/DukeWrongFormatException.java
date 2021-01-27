public class DukeWrongFormatException extends DukeException {
    private final String heading = "     Wrong format! Please follow this format: ";
    private final String deadlineFormat = "deadline (taskName) /by (YYYY-MM-D TIME)";
    private final String eventFormat = "event (taskName) /at (start time) (end time)";
    private String command;

    DukeWrongFormatException(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        String format = (command.equals("deadline")) ? deadlineFormat : eventFormat;
        return heading + format + "\n";
    }
}