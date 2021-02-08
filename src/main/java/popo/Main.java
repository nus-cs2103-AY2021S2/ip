package popo;

import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.stage.Stage;
import popo.storage.InvalidStorageFilePathException;
import popo.storage.Storage;
import popo.storage.StorageException;
import popo.tasks.TaskList;
import popo.ui.Ui;


/**
 * The main entry point to the chatbot application.
 * Initializes the application and starts user interaction.
 */
public class Main extends Application {
    private static final String MESSAGE_TERMINATE = "Press OK to terminate the programme.";
    private static final String ERROR_BOX_TITLE = "Error";

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    @Override
    public void init() throws Exception {
        super.init();
        Application.Parameters applicationParameters = getParameters();
        List<String> parameters = applicationParameters.getUnnamed();
        String[] args = parameters.toArray(new String[0]);
        initialize(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ui.start(primaryStage);
    }

    /**
     * Initializes the required components and prints the welcome greeting.
     *
     * @param args An optional user-specified filepath used to initialize the storage.
     */
    private void initialize(String[] args) {
        try {
            storage = initializeStorage(args);
            taskList = storage.loadTasks();
            ui = new Ui(storage, taskList);
        } catch (InvalidStorageFilePathException | IOException | StorageException ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage() + "\n" + MESSAGE_TERMINATE,
                    ERROR_BOX_TITLE,
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
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
