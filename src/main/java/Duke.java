/**
 * Duke is a AI assistant program that allows users to take note of their tasks.
 * Functions supported include:
 * - Creating tasks: todos, events, deadlines
 * - Marking tasks as done
 * - Deleting tasks
 * - Showing the whole list of tasks
 * - Error checking for IO
 */
public class Duke {

    private static Ui ui = new Ui();
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor method.
     */
    public Duke() {
        try {
            this.storage = new Storage();
            taskList = new TaskList(storage.load());
        } catch (DukeIoException e) {
            ui.showLoadingError(e);
            taskList = new TaskList();
        }
    }

    /**
     * Gets Duke's response based on user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeWrongInputException e) {
            return ui.showLoadingError(e);
        } catch (DukeMissingInputException e) {
            return ui.showLoadingError(e);
        } catch (DukeIoException e) {
            return ui.showLoadingError(e);
        }
    }
}
