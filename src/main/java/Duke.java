package main.java;

import java.util.NoSuchElementException;

import main.java.command.Command;


/**
 * Main class
 * Duke is a task management system that employs a friendly UI to help you manage your tasks
 */
public class Duke {
    private Ui ui;
    private TaskManager tm;
    private Parser parser;

    private Duke(String filepath) {
        this.ui = new Ui();
        this.tm = new TaskManager(filepath);
        this.parser = new Parser();
    }

    private void run() {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.displayLine();
                Command c = parser.parse(fullCommand);
                c.execute(tm, ui);
                isExit = c.isExit();
            } catch (NoSuchElementException e) {
                ui.displayExit();
                break;
            } catch (Exception e) {
                ui.displayError(e.getMessage());
            } finally {
                ui.displayLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("src/data/duke.txt").run();
    }
}
