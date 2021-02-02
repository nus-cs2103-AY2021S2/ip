import java.io.FileNotFoundException;

/**
 * Duke keeps track of a list of tasks.
 */
public class Duke {

    /** List of tasks. */
    private TaskList tasks;

    /** Storage that controls saving and reading file. */
    private Storage storage;

    /** Parser that processes commands. */
    private Parser parser;

    /** User Interface that handles interaction with user. */
    private Ui ui;

    /**
     * Initializes a newly created Duke object with empty task list, storage and parser.
     */
    public Duke() {
        tasks = new TaskList();
        storage = new Storage(tasks);
        parser = new Parser(tasks);
    }

    /**
     * Main method. Creates Duke object and runs the application.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        try {
            duke.storage.loadData();
            duke.parser.printList();
        } catch (FileNotFoundException e) {
            System.out.println("File not found :(");
        }
        duke.ui = new Ui();
        String userInput = duke.ui.nextInput();
        while (!"bye".equals(userInput)) {
            duke.parser.processCommand(userInput);
            duke.storage.writeTaskList();
            userInput = duke.ui.nextInput();
        }
        duke.ui.close();
    }
}
