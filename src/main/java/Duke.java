import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Duke project.
 */
public class Duke {

    private final Ui ui;
    private final Storage storage;
    private final Parser parser;

    public Duke() throws IOException {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        storage.readOrCreateFile();
    }

    /**
     * Generates a response to user input.
     * @param input The user input.
     * @return Return the correct output from the given input.
     * @throws FileNotFoundException Exception thrown when the file does not exist, should not happen.
     */
    String getResponse(String input) throws FileNotFoundException {
        String result = parser.processInput(input, storage.tasks, ui);
        storage.writeListIntoFile();
        return result;
    }
}

