package main.java;

import main.java.command.Command;

public class Duke {
    private Ui ui;
    private TaskManager tm;
    private Parser parser;

    public Duke(String filepath) {
        this.ui = new Ui();
        this.tm = new TaskManager(filepath);
        this.parser = new Parser();
    }

    public void run() {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.displayLine();
                Command c = parser.parse(fullCommand);
                c.execute(tm, ui);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.displayError(e.getMessage());
            } finally {
                ui.displayLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("src/main/data/storage.txt").run();
    }
}
