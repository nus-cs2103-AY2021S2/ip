/**
 * Class that can create Duke objects that help the user
 * to maintain a list of tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    /**
     * Constructor that creates a Duke object.
     *
     * @param filePath the file path of the file that is used
     *                 to store the task data.
     */
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        taskList = storage.readDataFromFile();
    }

    /**
     * Method to run the Duke application.
     */
    public void run() {
        ui.launchUI(taskList);
        storage.writeDataIntoFile(taskList);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
