package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;
import duke.exception.CommandException;


public class Duke {
    private Parser p;
    private Storage s;
    private TaskList list;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        s = new Storage();
        p = new Parser();
        try {
            list = new TaskList(s.loadData());
        } catch (FileNotFoundException e) {
            list = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        boolean isBye = false;
        while (!isBye) {
            try {
                Command cmd = p.parse(ui.getInput());
                cmd.execute(ui, s, list);
                isBye = cmd.getIsBye();

            } catch (CommandException e) {
                ui.showError(e.getMessage());
            } catch (IOException | NumberFormatException e) {
                ui.showError("Please enter a valid number!");
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
