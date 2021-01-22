package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {
    private TaskManager tm;
    private Ui ui;
    private Storage st;

    public Duke(String filePath) {
        ui = new Ui();
        tm = new TaskManager();
        st = new Storage(filePath);
        try {
            tm.loadArray(st.load());
        } catch (DukeException e) {
            tm.clear(); //clear tm if error loading file;
            ui.showError(e.getMessage());
        }
    }

    public void run() {
        ui.showWelcome();

        boolean done = false;
        while (!done) {
            try {
                String command = ui.nextCommand();
                ui.showLine();
                Command c = Parser.parse(command);
                c.execute(ui, tm, st);
                done = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
