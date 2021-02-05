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
            tasks = new TaskList(storage.load());
        } catch (IOException ie) {
            ui.printStorageIoError();
            System.exit(0);
        } catch (DukeException de) {
            ui.printDukeException(de);
            tasks = new TaskList();
        }
    }

    /**
     * Driver method for the Duke bot.
     */
    public void run() {
        ui.printIntroduction();
        boolean isExit = false;

        while (!isExit) {
            try {
                String stringCommand = ui.readCommand();
                Command command = Parser.parseCommand(stringCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException de) {
                ui.printDukeException(de);
            } catch (IOException ie) {
                ui.printUpdateIoError(ie);
            }
        }

        ui.closeScanner();
        ui.printExitMessage();
    }

    public String getResponse(String input) {
        try {
            //String stringCommand = ui.readCommand();
            Command command = Parser.parseCommand(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException de) {
            ui.printDukeException(de);
        } catch (IOException ie) {
            ui.printUpdateIoError(ie);
        }
        return "Failed?";
    }
}