import java.io.IOException;

import percy.command.Command;
import percy.command.Parser;
import percy.exception.PercyException;
import percy.storage.Storage;
import percy.task.TaskList;
import percy.ui.Ui;

public class Percy {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs the chat bot Percy.
     */
    public Percy() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Runs the chat bot which accepts commands and replies accordingly.
     *
     * @throws IOException
     */

    private String run(String command) throws IOException, PercyException {
        Parser parser = new Parser(command);
        Command cmd = parser.getCommand();
        String s = cmd.execute(tasks, storage);
        return s;
    }

    public String getResponse(String command, Percy percy) {
        String s = "";
        try {
            s = percy.run(command);
        } catch (IOException e) {
            s = e.toString();
        } catch (PercyException e) {
            s = e.toString();
        }
        return s;
    }

    public Storage getStorage() {
        return storage;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public Ui getUi() {
        return ui;
    }
}
