public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            storage.loadTasksFromFile(tasks);
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    public void handleUserInput() {
        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = ui.nextUserInput();
                Command command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                isRunning = !command.isExitCommand();
            } catch (DukeException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    public void run() {
        ui.printGreeting();
        handleUserInput();
        ui.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
