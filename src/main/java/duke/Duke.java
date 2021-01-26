package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Handles the main program logic of the Duke task manager program
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for a new Duke instance. Takes as a single parameter, <code>filePath</code>, which determines the
     * location from which tasks will be read from or saved to hard disk
     *
     * @param filePath relative file path of <code>.txt</code> file where tasks will be saved/loaded from
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser(ui, storage, tasks);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Handles the running of the Duke program by continually fetching user commands, parsing them and then executing
     * them
     */
    public void run() {
        this.ui.displayWelcomeMessage();

        while (true) {
            String userCommand = this.ui.getUserCommand();
            boolean exit = this.parser.parse(userCommand);
            try {
                this.storage.save(this.tasks.getTaskList());
            } catch (DukeException e) {
                ui.showSavingError();
            }
            if (exit) {
                break;
            }
        }
    }
}
