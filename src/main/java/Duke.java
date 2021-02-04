public class Duke {

    private boolean isActive;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke() {
        this.isActive = true;
        this.storage = new Storage();
        this.taskList = new TaskList(storage.loadTasks());
        this.ui = new Ui();
        this.parser = new Parser(taskList, ui);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

    private void start() {
        ui.greet();
        while (isActive) {
            String input = ui.askForInput();
            try {
                isActive = parser.parse(input);
                storage.saveTasks(taskList.getList());
            } catch (DukeException e) {
                ui.displayError(e.getMessage());
            }
        }
    }
}
