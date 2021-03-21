package antonio;

import java.io.IOException;

import antonio.command.Command;

/**
 * Represents the Antonio chat bot.
 */
public class Antonio {

    private boolean isRunning;
    private TaskList taskList;
    private final Storage storageHandler;
    private final Ui ui;

    /**
     * Constructor for the Antonio chat bot.
     */

    public Antonio() {
        isRunning = true;
        String path = "./data/TaskListData.txt";
        storageHandler = new Storage(path);
        ui = new Ui();
        try {
            taskList = storageHandler.open();
        } catch (AntonioException e) {
            taskList = new TaskList();
        }
        ui.displayWelcomeMessage();
    }

    /**
     * Checks if the bot is running.
     * @return A boolean value representing if the chat bot is running.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Gets a response from the chat bot based on an input string.
     * @param input Input to the chat bot from a user.
     */
    public String getResponse(String input) {
        Command command;
        try {
            command = Parser.parse(input);
            assert command != null : "Parser.parse should return a command";
            isRunning = !command.shouldExit();
            taskList = command.execute(taskList);
            storageHandler.write(taskList);
            return command.getResponse();
        } catch (AntonioException | IOException e) {
            return e.getMessage();
        }
    }

    public Ui getUi() {
        return ui;
    }

}
