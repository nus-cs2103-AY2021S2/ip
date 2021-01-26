package main.java.duke;

import main.java.duke.command.Command;
import main.java.duke.subfiles.Parser;
import main.java.duke.subfiles.Storage;
import main.java.duke.subfiles.TaskList;
import main.java.duke.subfiles.Ui;

/**
 * The Duke program is an interactive application which
 * enables users to store and modify their tasks.
 *
 * @author  arsatis
 * @version 1.0
 * @since   2021-01-19
 */
public class Duke {
    /** Task list which manages the tasks created by user input */
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public Duke(String path, String filename) {
        taskList = new TaskList();
        storage = new Storage(path, filename);
        ui = new Ui();
    }

    public void run() {
        boolean isExit = false;

        ui.greet();
        storage.loadData(taskList);
        while (!isExit) {
            String s = ui.readCommand();
            Command c = Parser.parse(s);
            c.execute(taskList, ui);
            isExit = c.isExit();
            ui.showLine();
        }
        storage.saveData(taskList);
        ui.bye();
    }

    /**
     * The main method which is executed when the Duke program
     * is run.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("../data/", "duke.txt").run();
    }

}
