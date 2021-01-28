import jeff.*;

public class Jeff {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Jeff(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JeffException e) {
            ui.printError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullMessage = ui.readMessage();
            try {
                isExit = Parser.execute(fullMessage, tasks, ui, storage);
            } catch (JeffException d) {
                ui.printError(d.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Jeff("data.txt").run();
    }
}
