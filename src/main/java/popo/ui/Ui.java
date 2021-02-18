package popo.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import popo.Main;
import popo.storage.Storage;
import popo.tasks.TaskList;

/**
 * Manages the UI component of the application.
 */
public class Ui {
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Creates a {@code Ui} object that handles the GUI.
     *
     * @param storage  {@code Storage} object that handles file operations.
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
            VBox mainWindow = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setComponents(storage, taskList, primaryStage);
            fxmlLoader.<MainWindow>getController().addWelcomeMessage();

            Scene scene = new Scene(mainWindow);
            scene.getStylesheets().add(Main.class.getResource("/view/Styles.css").toExternalForm());

            setStageTitleAndSize(primaryStage);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setStageTitleAndSize(Stage primaryStage) {
        primaryStage.setTitle("Popo");
        primaryStage.setMinWidth(500.0);
        primaryStage.setMinHeight(400.0);
    }
}
