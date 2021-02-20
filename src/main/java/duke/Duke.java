package duke;


import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;


/**
 * Represents the Duke CLI personal task assistant.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor. Starts an instance of Duke.
     */
    public Duke() {
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
            String dukeResponse = command.execute(tasks, storage);
            return dukeResponse;
        } catch (DukeException e) {
            return "Oops " + e.getMessage();
        }
    }
}
