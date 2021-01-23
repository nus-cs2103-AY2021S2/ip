package duke.exceptions;

public class CommandNotValidException extends DukeException {
    public CommandNotValidException() {
        super("\tCommand not valid. Please use \"todo\", \"deadline\"\n"
                + "\tor \"event\" followed by task description to add new tasks.\n"
                + "\tPlease use \"list\" to view your list of tasks.\"\n"
                + "\tPlease use \"done\" followed by index to mark completed tasks.\n"
                + "\tPlease use \"delete\" followed by index to delete tasks.\n"
                + "\tPlease use \"bye\" to exit.\n");
    }
}
