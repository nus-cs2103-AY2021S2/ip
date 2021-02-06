/**
 * The type Duke.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiates a new Duke.
     *
     * @param filePath the file path
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BaseException e) {
            ui.showError(e.toString());
        }
    }
    /**
     * Run.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommmand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommmand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BaseException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Duke("src/main/java/data.txt").run();
    }
}
