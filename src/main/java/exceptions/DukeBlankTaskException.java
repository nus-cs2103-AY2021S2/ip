package exceptions;

public class DukeBlankTaskException extends Exception {
    /**
     * Add default generate serial verion uid
     */
    private static final long serialVersionUID = 1L;

    public DukeBlankTaskException(String message) {
        super(message);
    }
}
