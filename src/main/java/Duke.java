import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke is a personal assistant chat bot that helps users to
 * keep track of various things.
 * @author Damith C. Rajapakse, Wu Weiming
 */
public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private boolean shouldExit = false;

    /**
     * Main method for Duke class.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            Duke d = new Duke("duke.txt");
            d.run();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Constructor for Duke class.
     *
     * @param filePath location where file with task list would be stored.
     * @throws IOException if filePath is invalid.
     */
    public Duke(String filePath) throws IOException {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        storage.retrieveTasks(taskList);
    }

    /**
     * Generates a response to user input.
     */
    public String getResponse(String input) throws IOException {
            parser = new Parser();
            storage = new Storage("duke.txt");
            this.shouldExit = parser.isExit(input);
            return parser.execute(input, taskList, storage);
    }


    /**
     * Runs the Duke application.
     *
     * @throws IOException if filePath or system input is invalid.
     */

    public void run() throws IOException {
        ui.printHello();
        storage = new Storage("duke.txt");
        parser = new Parser();

        storage.storeTasks(taskList);
    }
}
