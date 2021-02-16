package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import duke.command.Command;
import duke.logging.Parser;
import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;

/**
 * A personal chat bot that helps a person keep track of various things.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks = new TaskList(new ArrayList<>());
    private final Ui ui;

    /**
     * Construct a RoBoBot chat bot
     * @param filePath   The file path where the tasks will be stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Execute the Duke chat bot.
     * @param input The command string.
     * @return      The response string.
     */
    public String run(String input) {
        try {
            Command command = Parser.parse(input);
            String message = (command.execute(tasks, ui, storage));
            System.out.println(message); // For CLI
            return message;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Get response from running the Duke program.
     * @param input  The command string.
     * @return       The response string.
     */
    public String getResponse(String input) {
        return new Duke("data/duke.txt").run(input);
    }
}
