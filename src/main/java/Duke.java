public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private CommandParser commandParser;
    private boolean isActive;
    
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.isActive = true;
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            this.tasks = new TaskList();
        }
        this.commandParser = new CommandParser(tasks, ui);
    }

    public static void main(String[] args) {
        Duke duke = new Duke("savedata.txt");
        duke.run();
    }

    private void run() {
        // display welcome sequence
        ui.welcome();

        String userInput;
        // loop until the user exits
        while (isActive) {
            // get user input
            userInput = ui.readCommand();
            try {
                isActive = commandParser.parseCommand(userInput);
                storage.saveTasks(tasks);
            } catch (Exception e) {
                ui.borderPrint(e.getMessage());
            }
        }
        
        // exit sequence
        ui.quit();
    }
}