package duke.exceptions;

public class DukeException extends Exception {
    public DukeException(String error) {
        super("\nMaster, I'm afraid I don't understand what you mean.");
    }
}
