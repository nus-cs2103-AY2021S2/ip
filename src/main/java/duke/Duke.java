package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.DukeResponses;

/**
 * class Duke
 * Description: A class to represent a chatbot program that keeps track of tasks
 */
public class Duke {
    private final String FILEPATH = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private DukeResponses dukeResponses;

    /**
     * Constructor: creates a new Duke program
     */
    public Duke() {
        dukeResponses = new DukeResponses();
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            dukeResponses.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * getResponse: Generates a response from Duke based on inputs from the user
     * @param userInput
     * @return a response from Duke
     */
    public String getResponse(String userInput) {
        assert userInput != null : "user input should not be null";
        try {
            Command c = Parser.parse(userInput);
            return c.execute(tasks, dukeResponses, storage);
        } catch (DukeException e) {
            return dukeResponses.showError(e);
        }
    }

    /**
     * getDukeResponses: Retrieves DukeResponses object
     * @return DukeResponses
     */
    public DukeResponses getDukeResponses() {
        return dukeResponses;
    }
}