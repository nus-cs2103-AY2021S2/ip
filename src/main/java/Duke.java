import java.io.IOException;

/**
 * Represents a Personal Assistant Chatbot named Duke that
 * helps users to keep track of their tasks
 * @author Damith C. Rajapakse, Jeffry Lum, Vanessa Tay
 */
public class Duke {

    public static boolean sayBye;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public static final String FILE_PATH_SAVED_TASKS = "./savedTasks.txt";

    /**
     * Loads the task list from hardware if it was previously created, else creates
     * a new task list
     * @param filePath  the file path in which the task list is loaded from, and will be stored into
     */
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

    /**
     * Runs the chatbot by listening to the user commands and responding accordingly
     * @throws DukeException  if user command is invalid
     */
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
