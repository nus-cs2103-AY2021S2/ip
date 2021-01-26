public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.syncFromFile());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") + "/duke.txt").run();
    }

    public void run() {
        ui.showWelcome();
        Processor processor = new Processor(tasks, storage, ui);

        boolean isOver = false;
        while (!isOver) {
            String input = ui.readCommand();
            ui.printLine();
            isOver = processor.processSentence(input);
            ui.printLine();
        }
    }
}
