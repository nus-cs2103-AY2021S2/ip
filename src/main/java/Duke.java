import java.util.NoSuchElementException;

public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        String line;
        Command c;
        try {
            while (!isExit) {
                line = ui.readLine();
                c = new Command(line);
                c.execute(ui, storage, taskList);
                isExit = c.isExit();
            }
        } catch (NoSuchElementException e) {
            ui.showGoodbye();
            return;
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Duke("data/duke_data.txt").run();
    }
}