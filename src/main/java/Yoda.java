public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        this.tasks = storage.load();
    }

    public void runDuke() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.readCommand();
            ui.showLine();
            Command c = Parser.parse(command);
            c.execute(tasks, ui, storage);
            ui.showLine();
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        String filePath = home+"/dukeTasks.txt";
        Duke duke = new Duke(filePath);
        duke.runDuke();
    }
}
