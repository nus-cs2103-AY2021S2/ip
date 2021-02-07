package bob;

/**
 * Bob is a program that helps you keep track of tasks.
 *
 * @author Sylvia
 * @version 0.1
 */
public class Bob  {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath This is the path of the file that stores the list
     *                 of tasks from the current working directory.
     */
    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return ui.respondToCommand(input, tasks, storage);
    }
}