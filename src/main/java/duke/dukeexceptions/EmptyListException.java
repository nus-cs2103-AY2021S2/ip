package duke.dukeexceptions;

public class EmptyListException extends DukeException {

    public EmptyListException() {
        super("Your TaskList is empty!");
    }
}
