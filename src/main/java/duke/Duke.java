package duke;

import duke.processintructions.Command;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.processintructions.Parser;
import duke.processintructions.CommandRouter;

/**
 * Duke is a todo list with features built incrementally.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    private static String storageFilePath = "/data/duke.tasks.txt";
    private static String absoluteStorageFilePath = System.getProperty("user.dir") + storageFilePath;

    public Duke() {
        ui = new Ui();
        storage = new Storage(absoluteStorageFilePath);
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
     *
     * @param input Input string typed by the user.
     * @return Output string generated by Duke in response.
     */
    public String getResponse(String input) {
        assert input != null;

        CommandRouter commandRouter = new CommandRouter();
        Command parsedCommand = Parser.parse(input);
        String response = commandRouter.route(parsedCommand, tasks, input);

        storage.save(tasks.getList());

        return response;
    }
}


