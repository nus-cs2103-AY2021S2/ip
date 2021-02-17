package duke;

/**
 * Duke is the main class that runs the whole program.
 *
 * @author Prabhakaran Gokul
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * This constructor initialises the Ui, Storage Classes
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.taskList = new TaskList(storage.loadFile());
            this.ui.loadingSuccess();
        } catch (DukeException e) {
            this.ui.showError(e.getMessage());
            this.taskList = new TaskList();
            this.ui.loadingFailure();
        }
    }

    /**
     * Drives the program.
     * Parser class in initialised here and user inputs are parsed and
     * processed to generate a response.
     */
    public String run (String input) {
        String dukeResponse;
        Parser exec = new Parser(taskList);
        try {
            dukeResponse = exec.executeCommand(input);
        } catch (DukeException e) {
            dukeResponse = e.getMessage();
        }
        if (exec.getIsExit()) {
            try {
                this.storage.saveFile(this.taskList.getListOfTasks());
            } catch (DukeException e) {
                dukeResponse = e.getMessage();
            }
        }
        return dukeResponse;

    }

    public String getResponse(String input) {
        return run(input);
    }


}
