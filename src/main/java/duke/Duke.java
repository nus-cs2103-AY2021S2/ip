package duke;

import java.io.IOException;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.FileIoException;
import duke.tasks.TaskList;

/**
 * Duke main class
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a Duke object and retrieves stored takes from disk if present
     *
     * @param filePath Path where task are stored in disk
     */
    public Duke(String filePath) throws FileIoException {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.getTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    private void run() throws DukeException, IOException {
        ui.printWelcomeText();
        boolean isBye = false;

        if (ui.hasNextCommand()) {
            while (!isBye) {
                String command = ui.getNextCommand();
                Parser parser = new Parser();
                Command c = parser.parse(command);
                c.execute(taskList);
                storage.writeFile(taskList);
                if (c instanceof ByeCommand) {
                    isBye = true;
                }
            }
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("data.txt").run();
    }
}
