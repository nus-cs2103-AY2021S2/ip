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

        assert tasks.size() >= 0 : "Task list not successfully initialized";
        this.commandParser = new CommandParser(tasks, ui);
    }

    /**
     * Generates a relevant response to the user input.
     * @param input Input from the user, as a String.
     * @return Relevant response from Duke, as a String.
     * @throws DukeException If an Exception occurs while processing the response.
     */
    public String getResponse(String input) throws DukeException {
        String response = commandParser.parseCommand(input);
        storage.saveTasks(tasks);
        return response;
    }
}