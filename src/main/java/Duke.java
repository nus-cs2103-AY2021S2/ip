import java.io.IOException;

/**
 * The Duke class is the main program responsible for instantiating all required classes
 * and methods.
 */
public class Duke {
    private final Storage ine;
    private final Ui ui;
    private final Parser parser;

    /**
     * Instantiates required classes.
     */
    public Duke() {
        this.ui = new Ui();
        TaskList tasks = new TaskList(ui);
        this.ine = new Storage(tasks, ui);
        this.parser = new Parser(tasks, ui);
    }

    /**
     * Creates a new Duke object and runs the main method.
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Main method.
     */
    public void run() {
        ui.welcome();
        try {
            ine.importData();
        } catch (IOException e) {
            ui.ioException();
        }

        parser.poll();

        try {
            ine.exportData();
        } catch (IOException e) {
            ui.ioException();
        }
        ui.bye();
    }
}