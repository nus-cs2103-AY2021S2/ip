package duke;

/**
 * A Personal Assistant Chatbot that helps the user to keep track of various things.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private TaskList tasks;

    public Duke(String filePath) {
        assert filePath.length() > 0 : "filePath cannot be empty";
        storage = new Storage(filePath);
        ui = new Ui();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (LoadTasksException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            return parser.handleInput(storage, ui, tasks, input);
        } catch (EmptyDescriptionException e) {
            return ui.getEmptyDescriptionErrorMsg();
        } catch (InvalidCommandException e) {
            return ui.getInvalidCommandErrorMsg();
        } catch (WriteTasksException e) {
            return ui.getWritingErrorMsg();
        }
    }
}












