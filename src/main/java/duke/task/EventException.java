package duke.task;

import duke.DukeException;

public class EventException extends DukeException {
    private static final long serialVersionUID = 1L;

    public EventException(String s) {
        super(s + "\nPlease re-enter the command in the format:\nEvent <event name> /at <YYYY-MM-DD>");
    }
}