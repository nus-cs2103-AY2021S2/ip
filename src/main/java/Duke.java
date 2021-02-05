import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for the Duke object
     */

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList(storage);
    }

    /**
     * Duke's exit message
     */

    public void exit() throws IOException {
        Ui.bye();
        this.storage.update(this.taskList);
    }

    /**
     * Run the Duke object
     */

    public void run() throws IOException {
        this.storage.createFile();
        Ui.greeting();
        this.taskList.addTaskFromFile();
    }

    public String getResponse(String input) throws IOException, DukeException {
        Parser parser = new Parser(this.taskList);
        System.out.println(this.taskList);
        return parser.parseUserCommand(input);
    }

}
