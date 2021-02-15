package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.DukeResponses;

/**
 * class Duke
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represent chatbot program that keeps track of tasks
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private DukeResponses dukeResponses;

    /**
     * Constructor: creates a new Duke program
     * @param filePath path where the list of tasks is saved
     */
    public Duke(String filePath) {
        dukeResponses = new DukeResponses();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            dukeResponses.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * getResponse: Generates a response from Duke
     * @param userInput
     * @return a response from Duke
     */
    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            return c.execute(tasks, dukeResponses, storage);
        } catch (DukeException e) {
            return dukeResponses.showError(e);
        }
    }

    /**
     * getDukeResponses: Retrieves DukeResponses
     * @return DukeResponses
     */
    public DukeResponses getDukeResponses() {
        return dukeResponses;
    }
}