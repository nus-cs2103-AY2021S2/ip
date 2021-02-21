package duke;

import duke.command.Command;
import duke.command.CommandResponse;
import duke.parser.CommandParser;

public class Duke {

    private Storage storage;
    private TaskList tasks;

    public Duke() {
        this("data/tasks.txt");
    }

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }

    public CommandResponse getResponse(String input) {
        try {
            Command c = CommandParser.getCommand(input);
            assert this.storage != null;
            assert this.tasks != null;
            return c.getResponse(this.tasks, this.storage);
        } catch (DukeException e) {
            String message = Ui.getErrorMessage(e.getMessage());
            return new CommandResponse(null, message, false);
        }
    }

    public String getGreeting() {
        return Ui.getGreeting();
    }

    public TaskList getTaskList() {
        return this.tasks;
    }
}
