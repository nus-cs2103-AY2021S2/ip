/**
 * Encompasses the behavior of the Duke chat-bot. Keeps track of Tasks in a List, tasks.
 * Stores tasks in "./data/duke.txt".
 */
public class Duke {
    private static TaskList tasks;
    private static Storage storage;
    private static Ui ui;
    private static Parser parser;

    public static void main(String[] args) {
        ui = Ui.startUi();

        storage = Storage.createStorage("./data/duke.txt");
        tasks = new TaskList();
        if (storage != null) {
            storage.loadTaskList(tasks);
        }

        parser = new Parser(tasks, storage);
        String command = "";
        while (!command.equals("bye")) {
            command = ui.readCommand();
            Ui.printLine();
            parser.parseCommand(command);
            Ui.printLine();
        }
        ui.close();
    }
}
