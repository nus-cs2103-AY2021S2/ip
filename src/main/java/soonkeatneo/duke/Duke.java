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
    private boolean isFirstLaunch;

    /**
     * Creates a new Duke object with the given file-path for {@Storage}.
     * @param filePath file path for initialization of Storage
     */
    public Duke(String filePath) {
        storage = new Storage(filePath).load();
        isFirstLaunch = false;
        if (storage.isSampleDataLoaded()) {
            isFirstLaunch = true;
        }
        tasks = new TaskList(storage);
    }

    /**
     * Returns True if this is the first launch, else False.
     * @return boolean representing if this is the first launch
     */
    public boolean isFirstLaunch() {
        return this.isFirstLaunch;
    }

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
