package duke;

import java.io.IOException;
import java.util.List;

import duke.storage.InvalidStorageFilePathException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

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
    public void start(Stage primaryStage) throws Exception {
        // Start the ui and show the GUI
        ui.start(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        // Exit the program
        exit();
    }

    /**
     * Initializes the required components and prints the welcome greeting.
     *
     * @param args an optional user-specified filepath used to initialize the storage
     */
    private void initialize(String[] args) {
        try {
            // Initialize the required components
            storage = initializeStorage(args);
            taskList = storage.loadTasks();
            ui = new Ui(storage, taskList);
            
            // Print the welcome greeting
            ui.printDivider();
            ui.printGreeting();
        } catch (InvalidStorageFilePathException ex) {
            ui.print(ex.getMessage());
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            ui.print("Failed to load tasks from storage file. Exiting...");
            throw new RuntimeException(ex);
        }
    }

    /**
     * Prints the exit message
     */
    private void exit() {
        ui.printExitMessage();
        ui.printDivider();
    }

    /**
     * Initializes the storage using the specified filepath. If user did not specify a filepath,
     * then the default filepath will be used.
     *
     * @param args an optional user-specified filepath used to initialize the storage
     * @return a Storage object that is used to read and write to a file
     * @throws InvalidStorageFilePathException if the specified filepath is invalid
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
