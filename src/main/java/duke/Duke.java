package duke;

import duke.customClass.Command;
import duke.customClass.Ui;
import duke.customClass.Storage;
import duke.customClass.TaskList;
import duke.customClass.Parser;
import duke.customClass.CommandRouter;

/**
 * Duke is a todo list with features built incrementally.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke() {
        ui = new Ui();
        storage = new Storage(System.getProperty("user.dir") + "/data/duke.tasks.txt");
        tasks = new TaskList();
        tasks.dataInput(storage.loadData());
    }

    public Duke(String path) {
        ui = new Ui();
        storage = new Storage(path);
        tasks = new TaskList();
        tasks.dataInput(storage.loadData());
    }

    /**
     * Returns the greeting message string
     */
    public String returnGreetingMessage() {
        return ui.greetingMessage();
    }

    /**
     * This method call each of the respective classes to execute a command that the user input
     */
    public String getResponse(String input) {
        CommandRouter commandRouter = new CommandRouter();
        Command parsedCommand = Parser.parse(input);
        String response = commandRouter.route(parsedCommand, tasks, input);

        storage.save(tasks.getList());

        return response;
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") + "/data/duke.tasks.txt").returnGreetingMessage();
    }
}


