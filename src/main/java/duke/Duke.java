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
     * Constructs a Duke chat bot.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
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
            String message = ui.printLine();
            Command command = Parser.parse(input);
            message += ("\n" + command.execute(tasks, ui, storage));
            message += ("\n" + ui.printLine());
            System.out.println(message); // For CLI
            return message;
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            ui.printLine();
        }
    }

    /**
     * Get response from running the Duke program.
     * @param input  The command string.
     * @return       The response string.
     */
    public String getResponse(String input) {
        return new Duke().run(input);
    }
}
