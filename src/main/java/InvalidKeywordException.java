/**
 * Exception that occurs when the user inputs an unaccepted keyword.
 */
public class InvalidKeywordException extends Exception {
    /** Helps the formatting of the output */
    private static final String SPACE = "     ";
    private static final String LINE = "\n     <<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>\n";

    /**
     * Constructor for the exception.
     */
    public InvalidKeywordException() {
        super(LINE + SPACE + "The supported keywords are: todo, deadline, event, done, list, delete, bye only." + LINE);
    }

    /**
     * Prints the error message.
     */
    public void printMessage() {
        System.out.println(super.getMessage());
    }
}
