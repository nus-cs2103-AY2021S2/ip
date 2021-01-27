import duke.*;

import java.nio.file.Path;
import java.util.*;

// potential exceptions to catch:
// 1) deleting a non-existent task
// 2) marking a non-existent task as done
// 3) marking an already done task done again


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Scanner sc;

    /**
     * Creates a Duke object
     * @param filePath path of local file
     */
    public Duke(Path filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadData());
            parser = new Parser(tasks);
        }
        catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Tasker program which allows user to interact with
     * to organize the user's Tasks.
     */
    public void run() {
        try {
            ui.greetUser();
            ui.indicateReady();
            sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] command = input.split(" ");
            while (!input.equals("bye")) {
                parser.receive(command);
                input = sc.nextLine();
                command = input.split(" ");
            }
            storage.saveData(tasks.getTasks());
            ui.sayGoodbye();
        }
        catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        finally {
            sc.close();
        }
    }

    /**
     * Main Function
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke(Storage.saveFilePath);
        duke.run();
    }
}


