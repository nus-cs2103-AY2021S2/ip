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

    /**
     * Duke class constructor
     *
     */
    public Duke() {
        this.storage = null;
        String filePath = System.getProperty("user.dir") + "/data/Duke.txt";
        initialiseDuke(filePath);
    }

    /**
     * Initialisation of Duke
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
     * Read and response to different inputs provided by the user accordingly
     *
     * @param input Text entered by the user in the GUI
     * @return Response message in string format
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

    /**
     * Greeting message from Duke Bot
     *
     * @return Greeting message in String format
     */
    public String greetUser() {
        return "Duke:\n Hello! I'm Duke\n" + "What can I do for you?";
    }
}

