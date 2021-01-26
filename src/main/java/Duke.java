import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a personal chatbot that helps the user keep track of their tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for this Duke object.
     * @param   filePath  File path to save task to hard disk.
     */
    public Duke(String filePath) {
        try {
            this.storage = new Storage(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.tasks = new TaskList();
        this.ui = new Ui();

    }

    /**
     * Runs Duke, allowing it to start serving the user.
     */
    public void run() throws DukeException, IOException {
        storage.initialise(tasks);
        ui.initialise();
        ui.tasksLeft(tasks);
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            Parser.parse(tasks, str);
            str = sc.nextLine();
        }
        storage.finalise(tasks);
        ui.finalise();
    }

    /**
     * Returns a string representation of the customer.
     */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke("./myData.txt").run();
    }
}
