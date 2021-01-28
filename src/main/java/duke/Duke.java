package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.exception.DukeException;

/**
 * A class represents the Duke bot.
 */
public class Duke {
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a Duke bot.
     * @param filePath The file path to store the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.syncFromFile());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Main method to lunch the bot.
     * @param args Arguments of the main method.
     */
    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") + "/duke.txt").run();
    }

    /**
     * Launch the Duke bot by reading in the user input and gives responses.
     */
    public void run() {
        boolean isOver = false;
        ui.showWelcome();
        while (!isOver) {
            try {
                String input = ui.readCommand();
                ui.printLine();
                Command command = Parser.parseCommand(input);
                command.execute(tasks, ui, storage);
                isOver = command.isExit();
                ui.printLine();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
