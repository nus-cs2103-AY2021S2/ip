import java.io.FileNotFoundException;
import java.io.IOException;
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) throws FileNotFoundException {
        Parser parser = new Parser(storage, ui, tasks);
        if (Parser.hasSaved(input)) {
            // TERMINATE APP
            storage.save(tasks.list);
            return ui.showSave();
        } else {
            return parser.parseCommand(input);
        }
    }

}


