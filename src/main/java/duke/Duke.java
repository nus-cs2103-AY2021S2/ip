package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * A Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {
    private final Storage storage;
    private TaskList taskList;

    /**
     * Constructs Duke chatbot.
     */
    public Duke() {
        storage = new Storage("data/tasks.txt");
        try {
            storage.createFileAndDirectory();
            taskList = new TaskList(storage.load());
        } catch (DukeException ex) {
            System.out.println("There was a problem loading up the storage.");
        }
    }

    /**
     * Runs Duke chatbot.
     *
     * @return Output for GUI.
     */
    public String run(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, storage);
        } catch (DukeException ex) {
            return ex.getMessage();
        }
    }
}
