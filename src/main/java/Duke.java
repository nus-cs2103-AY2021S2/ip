import java.util.Scanner;

public class Duke {
    public Storage storage;
    public TaskList taskList;
    public Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadData());
    }

    public static void main(String[] args) {
        final String PATH = "./data/duke.txt";
        Duke duke = new Duke(PATH);
        duke.ui.showWelcomeMessage();

        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String userInput = scan.nextLine();
            Command command = Parser.parseCommand(userInput);
            command.execute(duke.taskList, duke.ui, duke.storage);
        }
    }
}

