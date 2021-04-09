import duke.Parser;
import duke.Storage;
import duke.TaskList;

public class DukeUi {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Runs DukeUi with a default file storage called "duke.txt"
     * @param filePath location of file.
     */
    public DukeUi(String filePath) {
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) {
            this.tasks = new TaskList();
        }
        this.parser = new Parser(storage, tasks);
    }

    public String getResponse(String input) {
        return this.parser.response(input);
    }

}
