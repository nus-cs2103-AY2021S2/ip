package exceptions;

public class DukeException extends Exception {
    private final String info;

    /**
     * Constructor for the Duke Exception.
     * @param message Message of the Duke Exception.
     */
    public DukeException(String message) {
        super(message);
        this.info = "";
    }

    /**
     * Constructor for the Duke Exception.
     * @param message Message of the Duke Exception.
     * @param info Additional Information of the Exception.
     */
    public DukeException(String message, String info) {
        super(message);
        this.info = info;
    }

    public String getInfo() {
        return this.info;
    }


}
