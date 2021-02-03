/**
 * Driver class. Creating a Duke object runs the application.
 */
public class Duke {
    private final Parser parser;

    /**
     * Duke constructor specifying the file path to save the task list to.
     */
    public Duke() {
        Storage storage = new Storage("data/tasks.txt");
        TaskList taskList = new TaskList();
        storage.readFromStorage(taskList);
        parser = new Parser(taskList, storage);
    }

//    /**
//     * Runs Duke application.
//     */
//    public void run() {
//        ui.runUi(taskList, storage);
//    }
//
//    /**
//     * Driver code. Creates and runs Duke.
//     * @param args Commandline arguments. Not used.
//     */
//    public static void main(String[] args) {
//        new Duke().run();
//    }

    public String getResponse(String input) {
        return parser.parse(input);
    }
}
