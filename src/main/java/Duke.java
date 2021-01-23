import java.io.IOException;

/**
 * Represents a Personal Assistant Chatbot named Duke that
 * helps users to keep track of things
 * @author Damith C. Rajapakse, Jeffry Lum, Vanessa Tay
 */
public class Duke {

    public static boolean sayBye;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public static final String FILE_PATH_SAVED_TASKS = "./savedTasks.txt";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            new TaskList(storage.load());
        } catch (IOException | DukeException e) {
            ui.showLoadingError();
            new TaskList();
        }
    }

    public void run() throws DukeException {
        while (!sayBye) {
            String input = ui.readUserCommand();
            Parser.handleUserCommand(input, ui);
            Storage.store(FILE_PATH_SAVED_TASKS);
        }
    }

    /**
     * Greets the user and begins listening to user commands
     * @param args supplies command-line arguments to the Chatbot
     */
    public static void main(String[] args) throws DukeException {
        new Duke(FILE_PATH_SAVED_TASKS).run();
    }


}
