/**
 * Duke is a program that helps you keep track of tasks.
 *
 * @author Sylvia
 * @version 0.1
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath This is the path of the file that stores the list
     *                 of tasks from the current working directory.
     */

    public Duke(String filePath) {
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
     * Duke starts to take in commands.
     */
    public void run() {
        ui.showGreetings();
        boolean toContinue = true;

        while (toContinue) {
            String userInput = ui.listenToCommand();
            toContinue = ui.respondToCommand(userInput, tasks, storage);
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
