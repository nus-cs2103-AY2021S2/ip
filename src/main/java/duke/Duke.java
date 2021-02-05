package duke;

import duke.command.CommandParser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * This is the main Duke class that runs the chatbot.
 */
public class Duke {
    private final Ui ui;
    private final Storage fio;

    /**
     * This is the constructor for Duke, storing a new Ui and new Storage.
     */
    public Duke() {
        this.ui = new Ui();
        this.fio = new Storage();
    }

    /**
     * This runs the Duke program to take in and execute inputs.
     */
    public void run() {
        this.ui.showIntro();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                CommandParser commandParser = CommandParser.parseLine(fullCommand, this.fio.getArrSize());
                commandParser.executeCommand(ui, this.fio);
                isExit = commandParser.canExit();
            } catch (DukeException e) {
                ui.showError(e.toString());
            }
        }
        fio.beginClose();
        fio.closeFile();
    }

    public static void main(String[] args) {

        new Duke().run();
    }
}
