package duke;

import duke.command.Command;
import duke.command.CommandResponse;
import duke.parser.CommandParser;
import duke.task.Task;

import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this("data/tasks.txt");
    }

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public String welcomeUser() {
        return ui.getGreeting();
    }

    public ArrayList<Task> getTasks() {
        return tasks.getList();
    }

    public CommandResponse getResponse(String input) {
        try {
            Command c = CommandParser.getCommand(input);
            assert this.storage != null;
            assert this.tasks != null;
            return c.getResponse(tasks, storage);
        } catch (DukeException e) {
            String message = ui.getErrorMessage(e.getMessage());
            return new CommandResponse(message, false);
        }
    }
}
