package duke.task;

import duke.DukeException;

public class ToDoException extends DukeException {
    private static final long serialVersionUID = -1674176699745504837L;

    public ToDoException(String s) {
        super(s + "\nPlease re-enter the command in the format: todo <task>");
    }
}