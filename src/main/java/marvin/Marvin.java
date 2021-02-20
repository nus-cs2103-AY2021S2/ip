package marvin;


import marvin.commands.Command;
import marvin.exception.DukeException;
import marvin.parser.Parser;
import marvin.storage.Storage;
import marvin.task.TaskList;


/**
 * Represents the Duke CLI personal task assistant.
 */
public class Marvin {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor. Starts an instance of Duke.
     */
    public Marvin() {
        try {
            storage = new Storage();
            tasks = storage.load();
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns a response to be displayed to
     * the user based on input from the user.
     * @param input The user input.
     * @return Response to be displayed to user.
     */
    public String getResponse(String input) {
        assert storage != null;
        assert tasks != null;

        try {
            Command command = Parser.parseCommand(input);
            String response = command.execute(tasks, storage);
            return response;
        } catch (DukeException e) {
            return "Oops " + e.getMessage();
        }
    }
}
