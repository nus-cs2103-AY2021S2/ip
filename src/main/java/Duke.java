import java.io.*;

/**
 * Duke is a AI assistant program that allows users to take note of their tasks.
 * Functions supported include:
 * - Creating tasks: todos, events, deadlines
 * - Marking tasks as done
 * - Deleting tasks
 * - Showing the whole list of tasks
 * - Error checking for IO
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private static Ui ui = new Ui();

    public Duke(String filePath) throws IOException {
        this.storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError(e);
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Duke Simulation
     * @throws IOException if error in user IO
     */
    public void run() throws IOException {
        storage.load();
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeWrongInputException e) {
                ui.showError(e.getMessage());
            } catch (DukeMissingInputException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        storage.save(taskList.getTaskList());
        ui.showBye();
    }

    /**
     * Main driver function.
     * @param args command line arguments
     * @throws IOException if error in user IO
     */
    public static void main(String[] args) throws IOException {
        new Duke("duke.txt").run();
    }
}