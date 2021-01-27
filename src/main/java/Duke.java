

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public static void main(String[] args) {
        final String dataFilePath = "data/duke.dat";
        new Duke(dataFilePath).run();
    }

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (StorageException | TaskParseException e) {
            // if the loading fails for any reason, just make a new empty list
            tasks = new TaskList();
        }
        parser = new Parser();
        ui = new TextUi();
    }

    public void run() {
        ui.showGreeting();

        boolean isExit = false;
        do {
            String input = ui.readCommand();
            Command cmd = parser.parseCmd(input);
            CommandResult cmdResult = cmd.execute(tasks, storage);
            ui.showCommandResult(cmdResult);
            isExit = cmdResult.isExit();
            try {
                storage.save(tasks.serialize());
            } catch (StorageException e) {
                ui.showError("Warning: failed to save tasks!");
            }
        } while (!isExit);

        ui.showFarewell();
    }
}
