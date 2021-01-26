package duke;

import java.util.Scanner;

import duke.command.Command;

/**
 * The Duke class is the entry point into the chatbot program.
 * It scans in user input and adds/deletes/lists tasks based on the user input.
 * It prints the action reply to the CLI.
 */
public class Duke {
    private static final String PATH_NAME = "./data/saved_task_list.txt";
    private Ui ui;
    private Storage storage;
    private TaskList taskList = new TaskList();
    private Parser parser = new Parser();

    /**
     * Create and initialize a Duke object that initializes the storage and ui.
     *
     * @param filePath Relative file path to the saved data file of tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            storage.initializeTaskList(taskList);
        } catch (DukeException e) {
            ui.printDukeException(e);
        }
    }

    /**
     * Processes user input.
     */
    public void run() {
        ui.printGreeting();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                Command toRun = parser.parse(input);
                toRun.run(storage, taskList);
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

