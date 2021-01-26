public class Duke {
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        taskList = storage.readDataFromFile();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run() {
        ui.launchUI(taskList);
        storage.writeDataIntoFile(taskList);
    }
}
