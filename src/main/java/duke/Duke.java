package duke;

import duke.commands.Command;
import duke.dukeexceptions.DukeException;
import duke.tasks.TaskList;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.Ui;

public class Duke {
    private static final String FILE_PATH = "./src/main/java/duke/tasks.txt";
    private static final Ui UI = new Ui();
    private static final Storage STORAGE = new Storage(FILE_PATH, UI);
    private static final TaskList TASKLIST = STORAGE.loadFromFile();

    private boolean isExit = false;

    /**
     * Gets input from the user via the GUI and processes it.
     */
    public String getResponse(String input) {
        try {
            Parser p = new Parser(TASKLIST, UI, STORAGE);
            Command c = p.parse(input);
            String output = c.execute();
            this.isExit = c.isExit();
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String introduction() {
        return "Hello! I'm Duke.";
    }

    public boolean isExit() {
        return this.isExit;
    }
}
