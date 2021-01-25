/**
 * Driver class for Duke project
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException ex) {
            ui.display(ex.toString());
            tasks = new TaskList();
        }
    }

    public void run() {
        boolean continueInput = true;
        while (continueInput) {
            String input = ui.nextCommand();
            try {
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                continueInput = command.continueInput();
            }
            catch (DukeException ex) {
                ui.display(ex.toString());
            }
        }
    }

    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir") + "/data/Duke.txt";
        new Duke(filePath).run();
    }
}
