package duke.bot;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeLoadException;

/** A chat bot that can help the user manage their tasks */
public class Duke {
    /** Name of the chat bot */
    private static final String CHATBOT_NAME = "Mantaro";
    /** Determine whether the chat bot continue to run */
    private boolean isActive;
    /** Manages a task list in the chat bot */
    private TaskManager taskManager;
    /** Provide different way of printing messages for the chat bot */
    private Ui ui;

    /** Constructor of Duke */
    public Duke() {
        isActive = true;

        ui = new Ui();
        taskManager = new TaskManager();
        Command.setup(ui, taskManager);

        try {
            Storage.loadTasksTo(taskManager);
        } catch (DukeLoadException e) {
            ui.constructErrorMessage(e.getMessage());
        }
    }

    public String getBootupMsg() {
        return ui.constructWelcomeMessage(CHATBOT_NAME);
    }

    /**
     * Return a response message when given an input command to execute on
     * @param input A string to be processed as a command
     * @return A string containing any output message from the bot (if any)
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute();
            isActive = !command.willExit();
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String getExitMsg() {
        return ui.constructGoodbyeMessage();
    }

    public boolean hasClosed() {
        return !this.isActive;
    }
}
