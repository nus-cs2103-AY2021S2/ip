import jeff.*;

/**
 * Main class for the Jeff chatbot.
 */
public class Jeff {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for the Jeff class.
     * Initializes the Storage, TaskList and Ui needed for the chatbot.
     *
     * @param filePath
     */
    public Jeff(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JeffException e) {
            ui.printError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Jeff chatbot until isExit is true.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            String fullMessage = ui.readMessage();
            try {
                isExit = Parser.execute(fullMessage, tasks, ui, storage);
            } catch (JeffException d) {
                ui.printError(d.getMessage());
            }
        }
    }

    /**
     * Main method for the Jeff chatbot.
     * Creates a new Jeff object and runs it.
     *
     * @param args Command line arguments passed in.
     */
    public static void main(String[] args) {
        new Jeff("data.txt").run();
    }
}
