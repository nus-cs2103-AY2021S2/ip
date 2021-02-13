package duke;

import java.io.File;
import java.io.IOException;

import duke.command.Command;
import duke.command.CommandResult;

/**
 * main class containing the Duke Chatbot main logic.
 */

public class Duke {
    private static Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;

    Duke() {
        this.storage = initializeStorage();
        this.ui = new Ui();
        try {
            tasks = new TaskList(storage.loadStorage());
        } catch (DukeStorageException err) {
            System.out.println(err.getMessage());
            tasks = new TaskList();
        } catch (DukeParseException err) {
            System.out.println(err.getMessage());
            System.exit(1);
        }
    }



    private Storage initializeStorage() {
        File directory = new File("data"); // Check if directory exists.
        if (!directory.exists()) {
            directory.mkdir();
        }
        File f = new File("data/duke.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Storage("data/duke.txt");
    }

    public String start(){
        return ui.getWelcomeMessage();
    }

    public String getResponse(String input) {
        try {
            Parser parser = new Parser(input);
            Command command = parser.parseCommand();
            CommandResult commandResult = command.execute(ui, tasks, storage);
            this.isExit = commandResult.getIsExit();
            return commandResult.getMessageToDisplay();

        } catch (DukeParseException e) {
            return "OOPS!!! " + e.getMessage();
        }
    }

    public boolean getIsExit() {
        return isExit;
    }
}
