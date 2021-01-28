package duke;

/**
 * The main duke.Duke class.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final Chatbot chatbot;

    Duke() {
        ui = new Ui();
        storage = new Storage("./data.txt");
        chatbot = new Chatbot(storage.readFile());
    }

    /**
     * Triggers the execution of duke.Duke.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Duke().execute();
    }

    /**
     * Executes all the functions needed for duke.Duke, including welcome,
     * chat bot execution, welfare, and update of file.
     */
    public void execute() {
        ui.welcome();
        chatbot.execute();
        ui.welfare();
        storage.updateFile(chatbot.getTaskList());
    }

}
