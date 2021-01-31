package duke.ui;

import java.io.IOException;

import duke.Main;
import duke.MainWindow;
import duke.storage.Storage;
import duke.tasks.TaskList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Handles the input/output of the application.
 * Responsible for getting user input and printing messages to the console.
 */
public class Ui {
    private static final String DIVIDER = "------------------------------------------------------------";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    private Storage storage;
    private TaskList taskList;

    public Ui(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }

    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            VBox vBox = fxmlLoader.load();
            Scene scene = new Scene(vBox);
            primaryStage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setComponents(storage, taskList, primaryStage);
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
