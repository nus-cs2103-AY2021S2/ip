package duke;

/**
 * Encompasses the behavior of the Duke chat-bot. Duke allows users to create different types of tasks in a todo-list,
 * and can do various functions on the list such as deleting and listing.
 * The list is saved by the Storage object at "./data/duke.txt".
 */
public class Duke {
    /**
     * Starts Duke with instance of Ui, Storage, TaskList, and Parser to receive input and serve output.
     * @param args
     */
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
