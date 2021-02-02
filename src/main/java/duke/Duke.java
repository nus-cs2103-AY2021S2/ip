package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
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
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data.txt");
        taskList = new TaskList(storage.getTasks());
    }

    private void run() {
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

    public String getResponse(String input) {
        Parser parser = new Parser();
        Command command = parser.parse(input);
        String response = command.execute(taskList);
        return response;
    }

}
