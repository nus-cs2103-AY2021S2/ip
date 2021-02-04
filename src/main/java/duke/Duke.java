package duke;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.fileSaver.FileSaver;
import duke.task.TaskList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class Duke extends Application{
    private TaskList task;
    private Ui ui;
    private FileSaver fs;

    public Duke (String folderName, String fileName) {
        fs = new FileSaver(folderName, fileName);
        ui = new Ui();
        task = new TaskList();
    }
    public void run() {
        ui.greeting();
        try {
            fs.load(task);
        } catch (DukeException e) {
            //TODO: handle exception
            ui.printErrorMessage(e.getMessage());
        }
        ui.runUi(task, fs);
        ui.close();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public static void main(String[] args) {
        new Duke("data", "duke.txt").run();
    }
}
