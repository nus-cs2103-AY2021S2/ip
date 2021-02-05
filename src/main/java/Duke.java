import Tasks.TaskList;

public class Duke {
    private final Storage storage;
    private final Ui ui;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
    }

    public void run() {
        TaskList tasks = this.storage.loadTasks();
        this.ui.run(tasks);
        this.storage.saveTasks(tasks);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
