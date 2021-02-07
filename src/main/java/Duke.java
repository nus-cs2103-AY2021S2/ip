import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Duke project.
 */
public class Duke {

    private final Ui ui;
    private final Storage storage;
    private final TaskParser taskParser;
    private final ContactParser contactParser;
    private int listType = 3;

    public Duke() throws IOException {
        ui = new Ui();
        storage = new Storage();
        taskParser = new TaskParser();
        contactParser = new ContactParser();
        storage.readOrCreateFile();
    }

    /**
     * Generates a response to user input.
     *
     * @param input The user input.
     * @return Return the correct output from the given input.
     * @throws FileNotFoundException Exception thrown when the file does not exist, should not happen.
     */
    String getResponse(String input) throws FileNotFoundException {
        if (input.equals("tasks")) {
            listType = 0;
            return "I got you!";
        } else if (input.equals("contacts")) {
            listType = 1;
            return "I got you!";
        } else {
            String result;
            if (listType == 0) {
                result = taskParser.processInput(input, storage.tasks, ui);
                storage.writeTaskListIntoFile();
            } else {
                result = contactParser.processInput(input, storage.contacts, ui);
                storage.writeContactListIntoFile();
            }
            return result;
        }
    }
}

