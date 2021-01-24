package jaryl.duke;

import jaryl.duke.DukeException;

public class EmptyListException extends DukeException {
    public EmptyListException() {
        super("It seems you have nothing on your list.");
    }
}
