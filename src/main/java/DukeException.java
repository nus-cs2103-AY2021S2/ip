/**
 * Indicates an error during Duke Application runtime.
 */
class DukeException extends Exception {

    public DukeException(String description) {
        super(description);
    }
}
