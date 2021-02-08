package duke;

import duke.command.Command;
import duke.subfiles.Parser;
import duke.subfiles.Storage;
import duke.subfiles.TaskList;
import duke.subfiles.Ui;

/**
 * The Duke program is an interactive application which
 * enables users to store and modify their tasks.
 *
 * @author  arsatis
 * @version 1.1
 * @since   2021-01-26
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
        String pathToFile = "../data";
        String filename = "duke.txt";

        taskList = new TaskList();
        storage = new Storage(pathToFile, filename);
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

    public Storage getStorage() {
        return storage;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Generates a response to user input.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        return c.execute(taskList, ui);
    }

    /**
     * The main method which is executed when the Duke program
     * is executed.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
