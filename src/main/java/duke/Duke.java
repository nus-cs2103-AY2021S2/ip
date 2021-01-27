package duke;

/**
 * Encompasses the behavior of the DukeObjects.Duke chat-bot. Keeps track of Tasks in a List, tasks.
 * Stores tasks in "./data/duke.txt".
 */
public class Duke {
    public static void main(String[] args) {
        Ui ui = Ui.startUi();

        Storage storage = Storage.createStorage("./data/duke.txt");
        TaskList tasks = new TaskList();
        if (storage != null) {
            storage.loadTaskList(tasks);
        }

        Parser parser = new Parser(tasks, storage);
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
