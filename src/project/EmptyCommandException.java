package project;

public class EmptyCommandException extends DukeException {

    EmptyCommandException(String s) {
        super("☹ OOPS!!! The description of a " + s + "cannot be empty.");
    }
}
