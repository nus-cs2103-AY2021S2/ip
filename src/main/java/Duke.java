import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new instance of Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        storage.checkFileExistence();
        tasks = new TaskList(storage.loadTasks());
    }

    /**
     * Initializes the app.
     */
    public void run() {
        ui.showWelcome();
        Scanner scan = new Scanner(System.in);
        Parser parser = new Parser(storage, tasks, ui);

        while (true) {
            String command = scan.nextLine();
            parser.handleCommand(command);
            if (command.equals("bye")) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
