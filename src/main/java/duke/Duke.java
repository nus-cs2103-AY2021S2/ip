package duke;

import commands.Command;
import exceptions.DukeException;

public class Duke {
    private TaskManager tm;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        tm = new TaskManager(ui);
    }

    public void run() {
        ui.showWelcome();

        boolean done = false;
        while (!done) {
            try {
                String command = ui.nextCommand();
                ui.showLine();
                Command c = Parser.parse(command);
                c.execute(ui, tm);
                done = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
