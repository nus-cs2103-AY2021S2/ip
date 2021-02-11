import java.io.IOException;

/**
 * Main driver class of the Duke program.
 * Keeps track of the tasks the user has and provide methods to modify
 * these tasks.
 */
public class Duke {
    private final Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor of the Duke class to initialise the bot.
     * Initialises the Ui, TaskList and Storage according to input path.
     * @param path Path of the file saved in hard drive that stores tasks.
     */
    public Duke(String path) {
        ui = new Ui();
        try {
            storage = new Storage(path);
            tasks = new TaskList(storage.loadNormalTasks(), storage.loadSnoozedTasks());
        } catch (IOException ie) {
            System.exit(0);
        } catch (DukeException de) {
            tasks = new TaskList();
        }
    }

    /**
     * Get response from Duke according to the input.
     * @param input Input from the user.
     * @return A String response from Duke.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException de) {
            return ui.printDukeException(de);
        } catch (IOException ie) {
            return ui.printUpdateIoError(ie);
        }
    }
}