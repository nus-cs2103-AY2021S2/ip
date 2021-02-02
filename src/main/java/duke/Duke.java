package duke;

public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private CommandParser commandParser;

    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }

        this.commandParser = new CommandParser(tasks, ui);
    }

    /**
     * Generates a relevant response to the user input.
     * @param input
     * @return
     * @throws DukeException
     */
    public String getResponse(String input) throws DukeException {
        String response = commandParser.parseCommand(input);
        storage.saveTasks(tasks);
        return response;
    }
}