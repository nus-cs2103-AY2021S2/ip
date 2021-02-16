package timmy;

import timmy.Commands.Command;
import timmy.Exceptions.DukeException;

import java.io.IOException;

public class Duke {

    private final Ui ui;
    private final Parser parser;
    boolean isExit = false;
    private Storage storage;
    private TaskList tasklist;
    private String message = "";

    public Duke(String filePath) {
        this.ui = new Ui();
        this.parser = new Parser();

        try {
            this.storage = new Storage(filePath);
            tasklist = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasklist = new TaskList();
        }
    }

    /**
     * Runs the program
     */
    public void run(String fullCommand) {
        String pendingMessage;
        try {
            Command c = parser.parse(fullCommand);
            pendingMessage = c.execute(tasklist, ui, storage);
            message = pendingMessage;
            isExit = c.isExit();
        } catch (IOException e) {
            message = ui.showLoadingError();
        } catch (DukeException | RuntimeException e) {
            message = ui.showError(e.getMessage());
        }
    }

    public String getMessage() {
        return message;
    }

    public String welcomeMessage() {
        return  ui.showWelcome();
    }
}
