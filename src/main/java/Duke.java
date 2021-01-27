import java.io.IOException;

public class Duke {
    private TaskList tasks;
    private Storage ine;
    private Ui ui;
    private Parser parser;

    public Duke() {
        this.ui = new Ui();
        this.tasks = new TaskList(ui);
        this.ine = new Storage(tasks, ui);
        this.parser = new Parser(tasks, ui);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

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