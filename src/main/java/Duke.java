import java.io.IOException;

/**
 * Duke is a personal AI assistant that allows users to record down their task list
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private static Ui ui = new Ui();

    public Duke(String filePath) throws IOException {
        this.storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError(e);
            taskList = new TaskList();
        }
    }

    public void run() throws IOException {
        storage.load();
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeWrongInputException e) {
                ui.showError(e.getMessage());
            } catch (DukeMissingInputException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        storage.save(taskList.getTaskList());
        ui.showBye();
    }

    public static void main(String[] args) throws IOException {
        new Duke("src/main/data/duke.txt").run();
    }
}