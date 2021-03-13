package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {
    private TaskManager tm;
    private Storage st;

    /**
     *  Duke constructor.
     *
     *  @param filePath Relative filepath to persistent storage.
     */
    public Duke(String filePath) {
        assert filePath.length() > 0 : "Empty Filepath";
        tm = new TaskManager();
        st = new Storage(filePath);
        try {
            tm.loadArray(st.load());
        } catch (DukeException e) {
            tm.clear(); //clear tm if error loading file;
        }
    }

    public String getResponse(String command) {
        String res;
        try {
            Command c = Parser.parse(command);

            if (c.isExit()) {
                return "";
            }

            res = c.execute(tm, st);
        } catch (DukeException e) {
            res = "Error: " + e.getMessage();
        }
        return res;
    }

    public static Duke init() {
        return new Duke("./data/tasks.txt");
    }

    public static String getWelcomeMessage() {
        return "Hello! This is Duke\n"
                + "What can I do for you?";
    }
}
