package duke;

import duke.command.Command;
import duke.exception.DukeStorageException;
import duke.util.MessageFormatter;
import duke.util.Parser;
import duke.util.TaskList;
import duke.util.TaskStorage;

/**
 * Class representing chat bot, Duke.
 */
public class Duke {
    private final TaskList tasks;
    private final TaskStorage storage;
    private final MessageFormatter messageFormatter;
    private boolean isActive;

    /**
     * Constructor of Duke.
     */
    public Duke() throws DukeStorageException {
        messageFormatter = new MessageFormatter();
        storage = new TaskStorage("data/tasks.txt");
        tasks = storage.retrieveData();
        isActive = true;
    }

    /**
     * Interprets user input, executes Command and returns Duke's response.
     *
     * @param input The input of the user.
     * @return String representing Duke's response.
     */
    public String getResponse(String input) {
        if (input.isBlank()) {
            return "You have not entered a command!";
        }
        Command cmd = Parser.parse(input, tasks);
        String response = cmd.execute(tasks, messageFormatter, storage);
        isActive = !cmd.toExit();
        return response;
    }

    /**
     * Gets the status of Duke.
     *
     * @return true if Duke is still running, false if Duke is no longer running.
     */
    public boolean isActive() {
        return isActive;
    }
}
