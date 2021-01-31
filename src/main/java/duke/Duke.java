package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {
    private TaskManager tm;
    private Ui ui;
    private Storage st;

    /**
     *  Duke constructor.
     *
     *  @param filePath Relative filepath to persistent storage.
     */
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

    private void run() {
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

    public String getResponse(String command) {
        String res;
        try {
            Command c = Parser.parse(command);
            res = c.execute(ui, tm, st);
        } catch (DukeException e) {
            res = e.getMessage();
        }
        return res;
    }


    public static Duke init(String filePath) {
        return new Duke(filePath);
    }


    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
