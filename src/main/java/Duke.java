import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showGreetings();
        boolean toContinue = true;

        while (toContinue) {
            String userInput = ui.listenToCommand();
            toContinue = ui.respondToCommand(userInput, tasks, storage);
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
