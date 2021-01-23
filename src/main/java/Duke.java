import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {



    private DataHandler storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Main class for programme to run.
     *
     * @param filePath path of file storage data
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new DataHandler(filePath);
        try {
            tasks = storage.loadData();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Tries to run Duke after filePath, taskList, storage and ui is given
     */

    public void run() throws DukeException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            Scanner sc = new Scanner(System.in);
            while (!isExit) {
                String fullCommand = sc.nextLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, fullCommand, storage);
                isExit = c.isExit();
            }
        }
    }

    /**
     * Main method to run whole programme
     *
     * @param args takes in arguments from user
     * @throws Exception of Duke (ie EmptyEventException)
     */

    public static void main(String[] args) throws Exception {
        new Duke("data/tasks.txt").run();
    }
}

