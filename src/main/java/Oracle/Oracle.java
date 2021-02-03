package Oracle;

import Command.Command;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Oracle {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private final String filePath = "./oracle_data.txt";
    /**
     * Create a new Oracle object
     */
    public Oracle() {

        ui = new Ui();
        parser = new Parser(ui);
        // storage holds the arraylist used to store all the tasks given to THE ORACLE
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the oracle until termination
     */
    public void run() {
        ui.showWelcome();
        // SCANNER takes input from user in a while loop, parses input using a series of if-else statements
        while (true) {
            String input = ui.readCommand();
            Command c = parser.parse(input);
            if (!c.execute(ui, tasks)){
                break;
            }
        }
        storage.store(tasks.getTasks());
    }

    public static void main(String[] args) {
        new Oracle().run();
    }

    String getResponse(String input) {
        System.setOut(new PrintStream(outContent));
        Command c = parser.parse(input);
        c.execute(ui, tasks);
        System.setOut(originalOut);
        String res = outContent.toString();
        outContent.reset();
        return res;
    }
}
