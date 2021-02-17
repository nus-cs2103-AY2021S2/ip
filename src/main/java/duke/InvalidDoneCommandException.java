package duke;

public class InvalidDoneCommandException extends DukeException {

    InvalidDoneCommandException() {

    }
    @Override
    public String getMessage() {
        return "Sorry :-( I can't mark that as done!\nCheck if the task number is valid.\nFormat: done <task number>";
    }
}
