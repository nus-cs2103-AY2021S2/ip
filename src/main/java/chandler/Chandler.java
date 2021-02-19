package chandler;

import chandler.command.Command;
import chandler.ui.Ui;

/**
 * The Chandler class represents a Chandler object that is responsible for
 * generating Chatbot output based on the user input.
 * It returns the action reply to the UI.
 */
public class Chandler {
    private Ui ui;
    private Storage storage;
    private TaskList taskList = new TaskList();
    private Parser parser = new Parser();

    /**
     * Create and initialize a Chandler object that initializes the storage and ui.
     *
     * @param filePath Relative file path to the saved data file of tasks.
     */
    public Chandler(String filePath) {
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
        } catch (ChandlerException e) {
            return ui.returnChandlerExceptionMsg(e);
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
        } catch (ChandlerException e) {
            return ui.returnChandlerExceptionMsg(e);
        }
    }


}

