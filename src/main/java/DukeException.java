
public class DukeException extends Exception {
    private String errorMessage;

    /**
     * Constructor for DukeException class
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }
}
