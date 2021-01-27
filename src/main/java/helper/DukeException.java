package helper;

public class DukeException extends Exception {

    String error;

    public DukeException(String errorMessage) {
        super(errorMessage);
        error = errorMessage;
    }

    public String showError() {
        return error;
    }
}