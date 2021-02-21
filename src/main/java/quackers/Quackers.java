package quackers;

import quackers.command.Command;
import quackers.command.CommandResponse;
import quackers.parser.CommandParser;
import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;

public class Quackers {

    private Storage storage;
    private TaskList tasks;

    public Quackers() {
        this("data/tasks.txt");
    }

    public Quackers(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (QuackersException e) {
            this.tasks = new TaskList();
        }
    }

    public CommandResponse getResponse(String input) {
        try {
            Command c = CommandParser.getCommand(input);
            assert this.storage != null;
            assert this.tasks != null;
            return c.getResponse(this.tasks, this.storage);
        } catch (QuackersException e) {
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
