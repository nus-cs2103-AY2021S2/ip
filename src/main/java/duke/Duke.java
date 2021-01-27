package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.exception.DukeException;


public class Duke {
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.syncFromFile());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") + "/duke.txt").run();
    }

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
