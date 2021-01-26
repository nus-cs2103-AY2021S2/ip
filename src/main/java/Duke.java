import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a personal chatbot that helps the user keep track of their tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList list;
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
        this.list = new TaskList();
        this.ui = new Ui();

    }

    /**
     * Runs Duke, allowing it to start serving the user.
     */
    public void run() throws DukeException, IOException {
        storage.initialise(list);
        ui.initialise();
        ui.tasksLeft(list);
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            Parser.parse(list, str);
            str = sc.nextLine();
        }
        storage.finalise(list);
        ui.finalise();
    }

    /**
     * Drives the program.
     */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke("./myData.txt").run();
    }
}
