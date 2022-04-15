package duke.exception;

public class InvalidDeleteCommandException extends DukeException {

    public InvalidDeleteCommandException() {
    }

    @Override
    public String getMessage() {
        return "I can't delete that for you :-(\nCheck if the task number is valid.\nFormat: delete <task number>";
    }
}
