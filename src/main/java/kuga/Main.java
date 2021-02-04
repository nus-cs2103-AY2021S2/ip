package kuga;

import java.io.IOException;
import java.util.List;

import kuga.storage.InvalidStorageFilePathException;
import kuga.storage.Storage;
import kuga.tasks.TaskList;
import kuga.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main entry point to the chatbot application.
 * Initializes the application and starts user interaction.
 */
public class Main extends Application {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    @Override
    public void init() throws Exception {
        super.init();
        // Get the command line argument
        Application.Parameters applicationParameters = getParameters();
        List<String> parameters = applicationParameters.getUnnamed();
        String[] args = parameters.toArray(new String[0]);

        // Initialize the required components
        initialize(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Start the ui and show the GUI
        ui.start(primaryStage);
    }

    /**
     * Initializes the required components and prints the welcome greeting.
     *
     * @param args An optional user-specified filepath used to initialize the storage.
     */
    private void initialize(String[] args) {
        try {
            // Initialize the required components
            storage = initializeStorage(args);
            taskList = storage.loadTasks();
            ui = new Ui(storage, taskList);
        } catch (InvalidStorageFilePathException | IOException ex) {
            // Exit the application if there is an error loading the storage file or reading from the file
            System.exit(1);
        }
    }

    /**
     * Initializes the storage using the specified filepath. If user did not specify a filepath,
     * then the default filepath will be used.
     *
     * @param args An optional user-specified filepath used to initialize the storage.
     * @return A {@code Storage} object that is used to read and write to a file.
     * @throws InvalidStorageFilePathException If the specified filepath is invalid.
     */
    private Storage initializeStorage(String[] args) throws InvalidStorageFilePathException {
        if (args.length > 0) {
            // User has specified a file path for the storage
            return new Storage(args[0]);
        } else {
            // Using the default file path as user did not specify a file path
            return new Storage();
        }
    }
}
