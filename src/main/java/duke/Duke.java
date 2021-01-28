package duke;

import duke.command.Command;

/**
 * Represents the main class of the application <code>Duke</code>.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class that initiates task storage locally.
     * @param filePath the path of the file that stores tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.openFile();
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Executes the command lines from a user.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Starts running the application Duke.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        new Duke("src/main/java/data/tasks.txt").run();
    }

}
