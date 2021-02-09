package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Duke information consisting of storage and tasks
 */
public class Duke {
    private static Storage storage;
    private static TaskList tasks;

    /**
     * Constructor for Duke
     */
    public Duke() {
        initializeDuke(System.getProperty("user.dir") + "/data/duke.txt");
    }

    /**
     * Sets up the data file storage path
     *
     * @param filePath File path directory
     */
    public void initializeDuke(String filePath) {
        storage = new Storage(filePath);
        assert storage != null : "Storage object not initialized";
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException dukeEx) {
            tasks = new TaskList();
        }
    }

    /**
     * Receives response from duke, depending on command
     *
     * @param input Input from user
     * @return A string response from Duke
     * @throws DukeException If there is parsing errors
     */
    public String getResponse(String input) throws DukeException {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, storage);
            return "Duke:\n" + c.getResponse();
        } catch (DukeException dukeEx) {
            return "Duke:\n" + dukeEx.toString();
        }
    }

    /**
     * Welcomes a user into the chat
     *
     * @return Welcome message from duke
     */
    public String welcomeMessage() {
        String output = "Duke:\n Hello! I'm Duke\n"
                + "What can I do for you?";
        return output;
    }
}
