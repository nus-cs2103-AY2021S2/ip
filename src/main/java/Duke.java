public class Duke {
    private final Storage storage;
    private final Ui ui;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
    }

    public void run() {
        TaskList existingTasks = this.storage.loadTasks();
        TaskList finalTasks = this.ui.run(existingTasks);
        this.storage.saveTasks(finalTasks);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
