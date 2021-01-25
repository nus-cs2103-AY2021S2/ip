package main.java;

import java.util.Scanner;

import main.java.subfiles.Storage;
import main.java.subfiles.TaskList;
import main.java.subfiles.Ui;
import main.java.subfiles.Parser;

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
    private Parser parser;

    public Duke(String path, String filename) {
        taskList = new TaskList();
        storage = new Storage(path, filename);
        ui = new Ui(taskList);
        parser = new Parser(ui);
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean hasInput = true;

        ui.greet();
        storage.loadData(taskList);
        while (hasInput) {
            String s = sc.nextLine();
            hasInput = parser.parse(s);
            System.out.println();
        }
        storage.saveData(taskList);
        ui.bye();

        sc.close();
    }

    /**
     * The main method which is executed when the Duke program
     * is run.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("../src/main/java/data/", "duke.txt").run();
    }

}
