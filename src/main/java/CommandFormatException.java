public class CommandFormatException extends MayaException {

    /**
     * Initialises a CommandFormatException.
     * Message is initialised to the default message.
     */
    public CommandFormatException() {
        super("The command you have entered did not follow the format. Please try again!");
    }

    /**
     * Initialises a CommandFormatException.
     * @param message a String representation of the custom error message.
     */
    public CommandFormatException(String message) {
        super(message);
    }
}
