
import java.io.IOException;

/**
 * Class initialises Duke Bot to take in inpiut from user
 */

public class Duke {

    private static Storage storage = new Storage();

    private static TaskList list = new TaskList();

    private static Parser parser = new Parser();

    /**
     * Presents saved history via Storage and
     * reads input from users.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        if (storage.isSavedHistory()) {
            storage.initialise(list);
        }
        parser.readInput(list);
        try {
            byeCommand();
        } catch (IOException io) {
            System.out.print("Could not save list!");
        }
    }

    /**
     * Function to call when we want to end main, to store the TaskList into storage
     *
     * @throws IOException
     */
    static void byeCommand() throws IOException {
        storage.saveHistory(list);
    }

}
