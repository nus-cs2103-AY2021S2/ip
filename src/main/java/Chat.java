public class Chat {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Chat(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChatException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.lines();
        ui.greet();
        ui.lines();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.lines(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ChatException e) {
                ui.showError(e);
            } finally {
                ui.lines();
            }
        }
    }

    public static void main(String[] args) {
        new Chat("data/tasks.txt").run();
    }
    
}
