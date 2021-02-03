package soonkeatneo.duke;

/**
 * Main implementation for the Duke chat-bot.
 * @author Soon Keat Neo
 * @version CS2103T AY20/21 Sem 2 iP v0.1
 */

public class Duke {
    /** Allows for easy change of the bot name in future. **/
    protected static final String BOT_NAME = "DukeNukem";
    private TaskList tasks;
    private Storage storage;

    /**
     * Creates a new Duke object with the given file-path for {@Storage}.
     * @param filePath file path for initialization of Storage
     */
    public Duke(String filePath) {
        storage = new Storage(filePath).load();
        try {
            tasks = new TaskList(storage);
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /*public static void main(String[] args) {
        new Duke("data/data.txt").run();
    }*/

    /**
     * Listens to the user's input, and passes it to the input handler.
     */
    public String handleInput(String inputString) {
        String message;
        try {
            message = Parser.parse(inputString, this.tasks, this.storage);
        } catch (InvalidCommandException | InvalidInputException | InvalidTaskException e) {
            return e.getMessage();
        }
        return message;
    }

}
