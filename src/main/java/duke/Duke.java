package duke;

import duke.command.Command;
import duke.main.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.UI;

/**
 * Main class for the duke project.
 * Disclaimer: This iteration of folder is heavily influenced by the folder structure
 *      and coding style of se-edu/addressbook-level2
 */
public class Duke {
    private Storage storage;
    private UI ui;

    /**
     * Constructor.
     */
    public Duke() {
    }

    /**
     * Main method for the code to start running.
     * @param args user input
     */
    public static void main(String[] args) {
        new Duke().run(args);
    }

    private void run(String[] args) {
        start();
        runUntilExit();
        exit();
    }

    private void start() {
        try {
            UI.greet();
            this.ui = new UI();
            this.storage = new Storage();
        } catch (DukeException e) {
            UI.printMessage(new String[] {e.getMessage()});
            UI.printMessage(new String[] {"Closing..."});
            System.exit(-1);
        }
    }

    private void runUntilExit() {
        String message = ui.getUserInput();
        while (!message.equalsIgnoreCase("bye")) {
            try {
                Command command = new Parser().parseMessage(message);
                String[] result = command.execute();
                UI.printMessage(result);
            } catch (DukeException e) {
                UI.printMessage(new String[] {e.getMessage()});
            }
            message = ui.getUserInput();
        }
    }

    private void exit() {
        UI.bye();
        System.exit(-1);
    }

}
