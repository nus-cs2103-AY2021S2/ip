public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            String fullMessage = ui.readMessage();
            try {
                isExit = Parser.execute(fullMessage, tasks, ui, storage);
            } catch (DukeException d) {
                ui.printError(d.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
