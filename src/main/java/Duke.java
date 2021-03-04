import java.io.IOException;
import java.util.Scanner;

/**
 * Main class
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructor method
     * @param filePath of the saved file
     * @throws IOException if user IO is incorrect
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Method to run Duke
     */
    public void run() throws IOException {
        Scanner sc = new Scanner(System.in);
        storage.loadTasks();
        ui.reply();
        Parser parser = new Parser(tasks, ui);
        while (true) {
            String command = sc.nextLine();
            parser.insertCommand(command);
            parser.process();
            if (parser.isEnd()) {
                break;
            }
        }
        storage.saveTasks(tasks);
        System.exit(0);
    }

    /**
     * Main method of Duke
     * @param args command line arguments
     */
    public static void main(String[] args) throws IOException {
        new Duke("data/DukeData.txt").run();
    }
}
