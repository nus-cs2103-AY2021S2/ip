import java.util.Scanner;

/**
 * Contains the main method to be run.
 */
public class Duke {
    public Storage storage;
    public TaskList taskList;
    public Ui ui;

    /**
     * Initialises the Duke object and loads hard disk data to current taskList.
     *
     * @param filePath the file path that specifies location in hard disk for storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadData());
    }

    /**
     * Reads user input and provides the logic for handling each user input.
     *
     * @param args takes in user input.
     */
    public static void main(String[] args) {
        final String PATH = "./data/duke.txt";
        Duke duke = new Duke(PATH);
        duke.ui.showWelcomeMessage();

        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String userInput = scan.nextLine();
            try {
                Command command = Parser.parseCommand(userInput);
                command.execute(duke.taskList, duke.ui, duke.storage);
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
}

