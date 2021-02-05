
import java.io.IOException;
import java.text.ParseException;


/**
   * Duke is the main class of the app
   * The main method runs the method run()
   * Which initiates the user for an input
   * This input would then be processed by parse to give a command
   * This command would then be executed
   */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage("data.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (NullPointerException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public void welcomeResponse() {
        ui.showWelcome();
    }

    public static void main(String[] args) throws IOException, ParseException {
       new Duke().welcomeResponse();
    }
}