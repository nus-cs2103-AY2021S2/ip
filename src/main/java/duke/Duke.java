package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Driver class for Duke project
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    public Duke() {
        this.storage = new Storage();
        String filePath = System.getProperty("user.dir") + "/data/Duke.txt";
        initialiseDuke(filePath);
    }

    /**
     * Duke class constructor
     *
     * @param filePath Path directory to location of storage file
     */
    public void initialiseDuke(String filePath) {
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage.loadData());
        } catch (DukeException ex) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts up the Duke Bot program, read and response to user various inputs accordingly
     */
    public String getResponse(String input) throws DukeException {
        try {
            Command command = Parser.parse(input);
            command.execute(this.tasks, this.storage);
            return "Duke:\n" + command.getMessage();
        } catch (DukeException ex) {
            return "Duke:\n" + ex.getMessage();
        }
    }

    public String greetUser() {
        String greeting = "Duke:\n Hello! I'm Duke\n" + "What can I do for you?";
        return greeting;
    }
}

