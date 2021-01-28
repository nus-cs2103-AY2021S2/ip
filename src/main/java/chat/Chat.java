package chat;

import chat.command.Command;
import chat.TaskList;
import chat.Storage;
import chat.Ui;

/**
 * Main class that executes Chat the Cat.
 */

public class Chat {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialise Chat the Cat by loading data from file to create list of tasks.
     * 
     * @param filePath Path of the file that is read from.
     */
    public Chat(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChatException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Method that starts and runs Chat the Cat.
     */
    public void run() {
        ui.lines();
        ui.greet();
        ui.lines();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.lines(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ChatException e) {
                ui.showError(e);
            } finally {
                ui.lines();
            }
        }
    }

    /**
     * Main method that initialises Chat the Cat and runs Chat the Cat.
     * @param args
     */
    public static void main(String[] args) {
        new Chat("data/tasks.txt").run();
    }
    
}
