import java.io.IOException;

/**
 * The Duke project.
 */
public class Duke {

    private final Ui ui;
    private final Storage storage;
    private final Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
    }

    /**
     * Main logic for the Duke project to run.
     * @throws IOException Throws IO exception.
     */
    public void run() throws IOException {
        ui.showWelcomeMsg();
        ui.showNameMsg();
        storage.readOrCreateFile();
        while(parser.canContinue) {
            ui.getPrompt();
            parser.processInput(Storage.tasks, ui);
        }
        storage.writeListIntoFile();
    }

//    public static void main(String[] args) throws IOException {
//        new Duke().run();
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }
}

