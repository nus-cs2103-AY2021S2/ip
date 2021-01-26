package duke;

import java.io.IOException;

import duke.command.Command;

/**
 * Represents the duke chat bot.
 */
public class Duke {

    private boolean isRunning;
    private TaskList taskList;
    private final Storage storageHandler;
    private final String path = "./data/TaskListData.txt";
    private Ui ui;

    /**
     * Constructor for the duke chat bot.
     */
    public Duke() {
        isRunning = true;
        storageHandler = new Storage(path);
        ui = new Ui();
        try {
            taskList = storageHandler.open();
        } catch (DukeException e) {
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
    public void getResponse(String input) {
        Command command;
        ui.printLine();
        try {
            command = Parser.parse(input);
            isRunning = !command.shouldExit();
            taskList = command.execute(taskList);
            storageHandler.write(taskList);
            ui.printResponse(command.getResponse());
            ui.printLine();
        } catch (DukeException | IOException e) {
            ui.printResponse(e.toString());
        }
    }

}
