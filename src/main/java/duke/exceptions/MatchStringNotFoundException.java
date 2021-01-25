package duke.exceptions;

public class MatchStringNotFoundException extends DukeException {
    public MatchStringNotFoundException() {
        super("\tPlease enter keyword of task description to find.\n");
    }
}
