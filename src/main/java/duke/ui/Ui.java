package duke.ui;

import duke.MainWindow;
import duke.storage.Storage;
import duke.tasks.TaskList;
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
    private MainWindow mainWindow;
    
    public Ui(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }
    
    public void start(Stage primaryStage) {
        mainWindow = new MainWindow(storage, taskList, primaryStage);
        mainWindow.show();
        mainWindow.loadUiComponents();
    }
}
