import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {

    private DataHandler storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new DataHandler(filePath);
        try {
            tasks = storage.loadData();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

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

    public static void main(String[] args) throws Exception {
        new Duke("data/tasks.txt").run();
    }
}

