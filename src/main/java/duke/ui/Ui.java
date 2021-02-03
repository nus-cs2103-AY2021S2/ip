package duke.ui;

import java.io.IOException;

import duke.Main;
import duke.storage.Storage;
import duke.tasks.TaskList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Manages the UI component of the application.
 */
public class Ui {
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Creates a {@code Ui} object that handles the GUI.
     *
     * @param storage {@code Storage} object that handles file operations.
     * @param taskList The loaded task list from storage.
     */
    public Ui(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Starts the GUI.
     *
     * @param primaryStage The primary stage of the application.
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
