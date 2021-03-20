package exception;

public class DukeInvalidInputException extends DukeException {

    private String invalidInput;

    public DukeInvalidInputException() {
        super();
    }

    /**
     * Constructs an Invalid Input Exception with the invalid input string
     */
    public DukeInvalidInputException(String invalidInput) {
        super(String.format("Invalid Input: %s", invalidInput));
        this.invalidInput = invalidInput;
    }

    public String getInvalidInput() {
        return invalidInput;
    }
}
