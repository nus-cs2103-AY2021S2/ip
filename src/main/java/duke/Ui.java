package duke;

/**
 * Ui class is responsible for outputs on the command line.
 * The main Duke class is able to use this to print out the necessary output.
 */
public class Ui {
    private static final String SPACING = "\t\t";
    private static final String GO_TO_NEXT_LINE = "\n";

    /**
     * An empty Ui constructor that instantiates an Ui class
     */
    public Ui() {

    }

    /**
     * showError method displays the exception messages caught.
     * @param errorMsg The content of the exception message
     */
    public void showError(String errorMsg) {
        System.out.println(SPACING + errorMsg + GO_TO_NEXT_LINE);
    }

    /**
     * Displays a welcome message that greets the user.
     */
    public void welcomeMsg() {
        String text = SPACING + "Hello! I'm Duke"
                    + GO_TO_NEXT_LINE + SPACING
                    + "What can I do for you?"
                    + GO_TO_NEXT_LINE;
        System.out.println(text);
    }

    /**
     * Formats and Repeats whatever message that is given as the input.
     * @param msg The message that need to be formatted and repeated
     */
    public void say(String msg) {
        System.out.println(SPACING + msg + GO_TO_NEXT_LINE);
    }

    /**
     * Formats and displays information about successful loading of file.
     */
    public void loadingSuccess() {
        System.out.println(SPACING + "Existing file successfully loaded" + GO_TO_NEXT_LINE);
    }
    /**
     * Formats and displays information about a failure loading the file.
     */
    public void loadingFailure() {
        System.out.println(SPACING + "Existing file could not be found. New file will be created"
                + GO_TO_NEXT_LINE);
    }

    /**
     * Greets the user goodbye.
     */
    public void byeMsg() {
        System.out.println(SPACING + "See you again soon!");
    }

}
