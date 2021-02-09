package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.tasks.TaskList;

/**
 * Duke main class
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a Duke object and retrieves stored takes from disk if present
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data.txt");
        taskList = new TaskList(storage.getTasks());
    }

    public String getResponse(String input) {
        Parser parser = new Parser();
        Command command = parser.parse(input);
        String response = command.execute(taskList);
        return response;
    }

}
