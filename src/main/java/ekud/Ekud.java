package ekud;

import ekud.command.Command;
import ekud.common.exception.EkudException;
import ekud.parser.Parser;
import ekud.storage.Storage;
import ekud.task.TaskList;

public class Ekud {
    private final Storage storage;
    private TaskList tasks;

    /**
     * Construct a new instance of the Ekud chatbot.
     *
     * @param filePath Path to the saved tasks
     */
    public Ekud(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (EkudException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String fullCommand) throws EkudException {
        Command c = Parser.parse(fullCommand);
        return c.execute(tasks, storage);
    }
}
