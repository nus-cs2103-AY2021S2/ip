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
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    private Storage storage;
    private TaskList taskList;

    /**
     * Creates a {@code Ui} object that handles the GUI.
     *
     * @param storage storage object that handles file operations
     * @param taskList the loaded task list from storage
     */
    public Ui(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Starts the GUI.
     *
     * @param primaryStage the primary stage of the application
     */
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            VBox vBox = fxmlLoader.load();
            Scene scene = new Scene(vBox);
            fxmlLoader.<MainWindow>getController().setComponents(storage, taskList, primaryStage);
            scene.getStylesheets().add(Main.class.getResource("/view/Background.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
