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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws FileNotFoundException {
        String result = parser.processInput(input, storage.tasks, ui);
        storage.writeListIntoFile();
        return result;
    }
}

