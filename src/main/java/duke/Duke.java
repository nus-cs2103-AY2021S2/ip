package duke;

import java.io.File;
import java.io.IOException;

import duke.command.Command;

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
        } catch (DukeException err) {
            System.out.println(err.getMessage());
            tasks = new TaskList();
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
        return ui.displayWelcomeMessage();
    }

    public String run(String input) {
        try {
            Parser parser = new Parser(input);
            Command command = parser.parseCommand();
            command.execute(ui, tasks, storage);
            this.isExit = command.shouldExit();
            return ui.getMessageToDisplay();

        } catch (DukeException e) {
            return "OOPS!!! " + e.getMessage();
        }
    }

    boolean shouldExit(){
        return isExit;
    }
}
