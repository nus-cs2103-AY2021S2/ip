package duke;

import duke.commands.ChatBotCommand;
import duke.exceptions.ChatBotException;

/**
 * The Duke program is an interactive application which
 * enables users to store and modify their tasks.
 */
public class Duke {
    private Storage storage;
    private TaskHandler th;

    /**
     *  Duke constructor.
     *
     *  @param filePath Relative filepath to persistent storage.
     */
    public Duke(String filePath) {
        assert filePath.length() > 0 : "Error: you must enter a filepath";
        storage = new Storage(filePath);
        th = new TaskHandler();
        try {
            th.loadTaskList(storage.loadTaskList());
        } catch (ChatBotException e) {
            th.clearTaskList();
        }
    }

    /**
     *  Prints the response from the bot.
     *
     *  @param input user input.
     */
    public String getResponse(String input) {
        String output;
        try {
            ChatBotCommand c = Parser.parse(input);

            if (c.isTerminated()) {
                return "Bye. Hope to see you again soon!";
            }
            output = c.runTask(th, storage);
        } catch (ChatBotException e) {
            output = "Error: " + e.getMessage();
        }
        return output;
    }

    public static String getWelcomeMessage() {
        return "Hello! I'm Duke\n"
                + "What can I do for you?";
    }
}
