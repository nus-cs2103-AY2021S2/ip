package duke.exceptions;

public class MatchStringNotFoundException extends DukeException {
    public MatchStringNotFoundException() {
        super("Please enter keyword of task description to find.");
    }
}
