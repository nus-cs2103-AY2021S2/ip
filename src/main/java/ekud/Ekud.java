package ekud;

import ekud.command.Command;
import ekud.common.exception.EkudException;
import ekud.parser.Parser;
import ekud.storage.Storage;
import ekud.task.TaskList;

/**
 * The main chatbot containing all logic to process all commands sent.
 */
public class Ekud {
    private final Storage storage;
    private TaskList tasks;
    private boolean isOnline;

    /**
     * Construct a new instance of the Ekud chatbot.
     *
     * @param filePath Path to the saved tasks
     */
    public Ekud(String filePath) {
        isOnline = true;
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (EkudException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Process the given command and return a summary if any.
     *
     * @param fullCommand The full command to be processed.
     * @return Summary of the completed transaction.
     * @throws EkudException If any exceptions are encountered during the execution.
     */
    public String getResponse(String fullCommand) throws EkudException {
        Command c = Parser.parse(fullCommand);
        if (c.isExit()) {
            isOnline = false;
        }

        assert isOnline;
        return c.execute(tasks, storage);
    }

    /**
     * Check if Ekud is still online for replies.
     *
     * @return True if no bye command has been executed, false otherwise.
     */
    public boolean isOnline() {
        return isOnline;
    }
}
