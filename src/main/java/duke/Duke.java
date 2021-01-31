package duke;

import duke.command.Command;
import duke.exception.DukeException;

import java.util.ArrayList;

/**
 * the agent program to run duke.Duke
 */
public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;

    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(FILE_PATH);

        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printMsg(e.getMessage());
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.greeting();

        boolean isExit = false;
        while(!isExit) {
            String fullCommand = ui.readCommand();
            ui.printLine();
            try {
                Command command = parser.parseCommand(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.printMsg(e.getMessage());
            }
            ui.printLine();
        }
    }
}
