package duke;

public class InvalidDeleteCommandException extends DukeException {

    public InvalidDeleteCommandException() {
    }

    @Override
    public String getMessage() {
        return "I can't delete that for you :-(\nCheck if the task number is less than the last number in the list";
    }
}
