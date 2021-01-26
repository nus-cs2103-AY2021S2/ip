package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;

import duke.exceptions.DukeException;
import duke.exceptions.FileIoException;

import duke.tasks.TaskList;

import java.io.IOException;

/**
 * Duke main class
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Duke object
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

    public void run() throws DukeException, IOException {
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
