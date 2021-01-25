import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage storage;
    public static TaskList taskList = new TaskList();
    private Parser parser = new Parser();

    private static final String PATH_NAME = "./data/saved_task_list.txt";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        ui.printGreeting();

        try {
            storage.initializeTaskList();
        } catch (DukeException e) {
            ui.printDukeException(e);
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                Command toRun = parser.parse(input);
                toRun.run(storage);
            } catch (DukeException e) {
                ui.printDukeException(e);
            }
            if (sc.hasNextLine()) {
                input = sc.nextLine();
            } else {
                break;
            }
        }
        sc.close();

        if (input.equals("bye")) {
            ui.printBye();
        }
    }

    public static void main(String[] args) {
        new Duke(PATH_NAME).run();
    }

}

