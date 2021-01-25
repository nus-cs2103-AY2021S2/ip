import java.io.IOException;
import java.util.Scanner;

/**
 * Driver class for Duke program
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException dukeEx) {
            ui.response(dukeEx.toString());
            tasks = new TaskList();
        }
    }

    public void run(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            try {
                ui.handleCommand(input, tasks, storage);
            } catch (DukeException dukeEx) {
                // Echoes out reason for invalid inputs
                ui.response(dukeEx.toString());
            }
        }
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") + "/data/duke.txt").run();
    }
}
