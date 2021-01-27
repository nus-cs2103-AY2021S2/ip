import java.io.FileNotFoundException;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Ui ui;

    public Duke() {
        tasks = new TaskList();
        storage = new Storage(tasks);
        parser = new Parser(tasks);
    }

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
