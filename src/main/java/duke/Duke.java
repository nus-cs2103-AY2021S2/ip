package duke;

import duke.command.Command;

/**
 * The Duke class is the entry point into the chatbot program.
 * It scans in user input and adds/deletes/lists tasks based on the user input.
 * It prints the action reply to the CLI.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList = new TaskList();
    private Parser parser = new Parser();

    /**
     * Create and initialize a Duke object that initializes the storage and ui.
     *
     * @param filePath Relative file path to the saved data file of tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }


    /**
     * Initializes the Chatbot when the program is just opened. Greets user and populates tasklist.
     *
     * @return Greeting message if successfully launched, error message otherwise.
     */
    public String initializeStart() {
        try {
            storage.initializeTaskList(taskList);
            return ui.returnGreeting();
        } catch (DukeException e) {
            return ui.returnDukeExceptionMsg(e);
        }
    }

    /**
     * Handles Chatbot's response to a user input.
     *
     * @param input User input.
     * @return Chatbot's response as String.
     */
    public String getResponse(String input) {
        try {
            Command toRun = parser.parse(input);
            return toRun.run(storage, taskList);
        } catch (DukeException e) {
            return ui.returnDukeExceptionMsg(e);
        }
    }


}

