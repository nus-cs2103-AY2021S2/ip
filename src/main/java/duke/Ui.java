package duke;

/**
 * Handles the User input and output
 */
public class Ui {

    private static final String logo = "\t ____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Constructor for a Ui.
     */
    public Ui(){
    }

    /**
     * The welcome prompt when Duke is first initialised
     */
    public String welcome() {
        return reply("Hello from Duke! What can I do for you?");
    }

    /**
     * Prints the error message for a caught CommandException
     * @param message the error message to be printed
     */
    public String showError(String message) {
        return reply(message);
    }

    /**
     * Replies with indentation and border lines
     * @param reply content of the reply
     */
    public String reply(String reply) {
        return reply;
    }
}
