import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The Duke class is the main program responsible for instantiating all required classes
 * and methods.
 */
public class Duke extends Application {
    private final Storage ine;
    private final Ui ui;
    private final Parser parser;

    /**
     * Instantiates required classes.
     */
    public Duke() {
        this.ui = new Ui();
        TaskList tasks = new TaskList(ui);
        this.ine = new Storage(tasks, ui);
        this.parser = new Parser(tasks, ui);
    }

    /**
     * Creates a new Duke object and runs the main method.
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Main method.
     */
    public void run() {
        ui.welcome();
        try {
            ine.importData();
        } catch (IOException e) {
            ui.ioException();
        }

        parser.poll();

        try {
            ine.exportData();
        } catch (IOException e) {
            ui.ioException();
        }
        ui.bye();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}