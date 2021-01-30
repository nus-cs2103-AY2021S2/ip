public class DukeIDKException extends DukeException{
    private String message;

    /**
     * Constructor for DukeException for incomprehensible request.
     */
    public DukeIDKException() {
        super("This is a Duke IDK exception");
        message = super.getMessage() + " "
                + "I don't get what you mean.";
    }

    /**
     * Returns message of the incomprehensible request.
     * @return Message of the incomprehensible request.
     */
    @Override
    public String getMessage() {
        return message;
    }
}
