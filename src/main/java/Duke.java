import java.io.IOException;
import java.util.Scanner;

/**
 * Duke is a personal assistant chat bot that helps users to
 * keep track of various things.
 * @author Damith C. Rajapakse, Wu Weiming
 */
public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;


    /**
     * Main method for Duke class.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            Duke d = new Duke("duke.txt");
            d.run();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Constructor for Duke class.
     *
     * @param filePath location where file with task list would be stored.
     * @throws IOException if filePath is invalid.
     */
    public Duke(String filePath) throws IOException {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        storage.retrieveTasks(taskList);
    }

    /**
     * Runs the Duke application.
     *
     * @throws IOException if filePath or system input is invalid.
     */
    public void run() throws IOException {
        ui.printHello();
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage("duke.txt");
        Parser parser = new Parser();

        while (true) {
            String input = sc.nextLine();
            boolean shouldExit = parser.parse(input, taskList);
            if (shouldExit) {
                break;
            }
        }
        sc.close();
        storage.storeTasks(taskList);
    }

}
