import java.io.IOException;

public class Duke {

    private final Ui ui;
    private final Parser parser;
    private Storage storage;
    private TaskList tasklist;
    private String message = "";
    boolean isExit = false;

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

    public String getLine() {
        return ui.showLine();
    }

    public String getMessage() {
        return message;
    }

    public String welcomeMessage() {
        return ui.showLine() + "\n" + ui.showWelcome() + "\n" + ui.showLine();
    }

    public String exitMessage() {
        return ui.showExit() + "\n" + ui.showLine();
    }

    public boolean getExitStatus() {
        return isExit;
    }
}
