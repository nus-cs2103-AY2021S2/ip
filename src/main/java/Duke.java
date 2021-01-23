import duke.Chatbot;
import duke.Storage;
import duke.Ui;

/**
 * The main Duke class.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final Chatbot chatbot;

    Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        chatbot = new Chatbot(storage.readFile());
    }

    /**
     * Trigger the execution of Duke.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Duke("./data.txt").execute();
    }

    /**
     * Execute all the functions needed for Duke, including welcome,
     * chat bot execution, welfare, and update of file.
     */
    public void execute() {
        ui.welcome();
        chatbot.execute();
        ui.welfare();
        storage.updateFile(chatbot.getTaskList());
    }

}
