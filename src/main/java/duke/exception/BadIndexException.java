package duke.exception;

public class BadIndexException extends Exception {
    private final int index;

    /**
     * Indicates that a requested index is out of bounds.
     *
     * @param index Index that was requested
     */
    public BadIndexException(int index) {
        this.index = index;
    }
    @Override
    public String getMessage() {
        return "Index " + (index + 1) + " is out of bounds.";
    }
}
