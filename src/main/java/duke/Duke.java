package duke;

import duke.command.Command;
import duke.subfiles.Parser;
import duke.subfiles.Storage;
import duke.subfiles.TaskList;
import duke.subfiles.Ui;

/**
 * The Duke program is an interactive application which enables users to store and modify their tasks.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class Duke {
    /** Task list which manages the tasks created by user input. */
    private TaskList taskList;

    /** Storage which manages the loading and storing of tasks. */
    private Storage storage;

    /** Ui which manages interactions with the user. */
    private Ui ui;

    /**
     * Default constructor for the Duke class.
     */
    public Duke() {
        taskList = new TaskList();
        storage = new Storage("../data/", "duke.txt");
        ui = new Ui();
    }

    /**
     * Runs the Duke program.
     */
    private void run() {
        boolean isExit = false;

        ui.greet();
        storage.loadData(taskList);
        while (!isExit) {
            String s = ui.readCommand();
            ui.showDots();
            Command c = Parser.parse(s);
            c.execute(taskList, ui);
            isExit = c.isExit();

            if (!isExit) {
                ui.showLine();
            }
        }
        storage.saveData(taskList);
        ui.bye();
    }

    /**
     * The main method which is executed when the Duke program is executed.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Returns the storage associated with this Duke object.
     *
     * @return The storage associated with this Duke object.
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Returns the task list associated with this Duke object.
     *
     * @return The task list associated with this Duke object.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Generates a response to user input.
     *
     * @param input User input.
     * @return Duke's response to the user input.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        return c.execute(taskList, ui);
    }

}
