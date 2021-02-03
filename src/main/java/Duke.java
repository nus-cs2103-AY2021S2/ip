import java.util.Scanner;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Initialization of the Storage, TaskList and Ui.
     * @param filePath The file path for storage of data.
     */
    public Duke(String filePath) {
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts the program to handle user interaction.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser p = new Parser(storage, tasks);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println(p.response(input));
            if (input.equals("bye")) {
                break;
            }
        }
        sc.close();
    }

    /**
     * Starts the program.
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

}
