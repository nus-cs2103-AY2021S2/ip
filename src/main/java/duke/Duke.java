package duke;

import java.io.IOException;

import duke.command.Command;
import duke.task.TaskList;

public class Duke {
    private Storage storage;
    private TaskList taskList;

    /**
     * Creates a Duke instance with filePath to where data is stored.
     *
     * @param filePath
     */
    public Duke(String filePath) throws IOException {
        storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch (DukeException e) {
            System.out.println(e.toString());
            taskList = new TaskList();
        }
    }

    public String getResponse(String input) throws DukeException {
        Command c = Parser.parse(input);
        return c.execute(taskList, storage);
    }
}
