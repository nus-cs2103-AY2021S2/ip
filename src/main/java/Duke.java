import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class initialises Duke Bot to take in input from user
 */

public class Duke {

    private static final Storage storage = new Storage();
    private static final TaskList list = new TaskList();
    private static final Parser parser = new Parser();

    /**
     * Presents saved history via Storage and
     * reads input from users.
     * @param args arguments
     * @throws Exception thrown when Duke cannot handle user input.
     */
    public static void main(String[] args) throws Exception {
        if (storage.isSavedHistory()) {
            storage.initialise(list);
        }
        try {
            byeCommand();
        } catch (IOException io) {
            System.out.print("Could not save list!");
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws Exception {
        return parser.respondToInput(input, list);
    }

    /**
     * Creates a new TaskList if Storage is present
     * to initialise the system
     * @return greeting String to be displayed to User
     * @throws FileNotFoundException
     */
    public String welcomeMessage() throws FileNotFoundException {
        if (storage.isSavedHistory()) {
            storage.initialise(list);
        }
        return "Hello! I'm Duke \n What can I do for you?";
    }


    /**
     * Function to call when we want to end main, to store the TaskList into storage
     *
     * @throws IOException from Storage.
     */
    static void byeCommand() throws IOException {
        storage.saveHistory(list);
    }

}
