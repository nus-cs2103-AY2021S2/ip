import Commands.Command;
import Tasks.TaskList;
import UserInterface.Ui;

public class Duke {
    private final Storage storage;
    private final Ui ui;
    private final TaskList tasks;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.tasks = this.storage.loadTasks();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String input = this.ui.readInput();
            Command c = new Parser(input).getCommand();
            c.execute(this.tasks, this.ui);
            isExit = c.isExit();
        }

        this.storage.saveTasks(this.tasks);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
