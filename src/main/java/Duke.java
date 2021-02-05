import java.io.IOException;

/**
 * Represents a Personal Assistant Chatbot named Duke that
 * helps users to keep track of their tasks
 * @author Damith C. Rajapakse, Jeffry Lum, Vanessa Tay
 */
public class Duke {
    protected static boolean isFinished;
    protected static final String FILE_PATH_SAVED_TASKS = "./savedTasks.txt";
    private Storage storage;
    private Ui ui;

    /**
     * Initializes a Duke object. If the specified filepath exists, tasks will be loaded
     * from the file into the current task list. Else, a new task list will be created.
     * @param filePath from which the tasks will be loaded from
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
     * Returns Duke's response to the user's input and stores any changes to the
     * task list back to the specified file
     * @param input supplied by the user
     * @return response to the user input
     * @throws DukeException if the user's input is invalid
     */
    public String getResponse(String input) throws DukeException {
        Storage.store(FILE_PATH_SAVED_TASKS);
        return Parser.handleUserCommand(input, ui);
    }

    public String greeting() {
        return Ui.greetUser();
    }
}
