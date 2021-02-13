package bob.processor;

import bob.BobException;
import bob.task.TaskList;

/**
 * Bob is a program that helps you keep track of tasks
 *
 * @author Sylvia
 * @version 0.1
 */
public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public TaskList getTasks() {
        return this.tasks;
    }

    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Constructor for Bob
     *
     * @param filePath This is the path of the file that stores the list
     *                 of tasks from the current working directory.
     */
    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (BobException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        return ui.respondToCommand(input, tasks, storage);
    }
}
