package dukeproject;

public class DukeException extends Exception {

    /**
     * Use as exception for duke.
     */
    private static final long serialVersionUID = -2154386125699489110L;

    public DukeException(String message) {
        super(message);
    }

    public DukeException() {
    }

}
