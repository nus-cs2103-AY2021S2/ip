package exception;

public class DukeInvalidInputException extends DukeException {

    private String invalidInput;

    public DukeInvalidInputException() {
        super();
    }

    public DukeInvalidInputException(String invalidInput) {
        super(String.format("Invalid Input: %s", invalidInput));
        this.invalidInput = invalidInput;
    }

    public String getInvalidInput() {
        return invalidInput;
    }
}
