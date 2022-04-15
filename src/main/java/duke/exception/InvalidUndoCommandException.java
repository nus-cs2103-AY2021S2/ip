package duke.exception;

public class InvalidUndoCommandException extends DukeException {

    public InvalidUndoCommandException() {

    }

    @Override
    public String getMessage() {
        return "Sorry. You cannot undo this command.\n"
                + "Commands that can be undone:\n1. done\n2. todo\n3. deadline\n4. event\n";
    }
}
