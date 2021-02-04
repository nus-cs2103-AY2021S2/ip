import controllers.Ui;
import models.Storage;

public class Duke {
    /** Constant to store the database path for Duke's commands */
    private static final String DATABASE_FILE_PATH = "data/duke.txt";
    /** Constant storing database directory path */
    private static final String DATABASE_DIRECTORY_PATH = "data/";
    /** Storage object to be passed to AppController for reading / writing to db */
    private final Storage storage;

    public Duke(String filePath, String directoryPath) {
        this.storage = new Storage(filePath, directoryPath);
    }

    /**
     * Main method to run Duke
     * @param args
     */
    public static void main(String[] args) {
        // creating new Duke run
        new Duke(DATABASE_FILE_PATH, DATABASE_DIRECTORY_PATH).run();
    }

    /**
     * Runs duke
     */
    public void run() {
        // Create new UI class
        Ui appController = new Ui(this.storage);
        // start app logic
        appController.start();

    }
}
