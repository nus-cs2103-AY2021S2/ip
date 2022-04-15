import duke.exception.NoCommandException;
import duke.util.*;
import duke.command.Command;

public class Duke {
    private static String FILE_PATH = "./data/";
    private static String FILE_NAME = "history.txt";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    private Duke(String filePath, String fileName) {
        ui = new Ui();
        storage = new Storage(filePath, fileName);
        taskList = new TaskList(storage.readPreviousFile());
    }

    public static Duke start(){
        return new Duke(FILE_PATH, FILE_NAME);
    }

    /**
     * Process command given by user.
     */
    public String doCommand(String sentence) {
        try {
            Command command = Parser.parseCommand(sentence, taskList);
            return command.execute(storage, taskList, ui, sentence);
        } catch (NoCommandException e) {
            return e.getMessage();
        }
    }
}
