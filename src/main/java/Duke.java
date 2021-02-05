public class Duke {
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Constructs Duke object, which immediately runs and starts accepting
     * user input.
     */
    public Duke() {
        storage = new Storage();

        TaskList tempTasks;
        try {
            tempTasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tempTasks = new TaskList(); // case where save file does not exist
        }

        this.tasks = tempTasks;
    }

    /**
     * Accepts the input and returns a String output representing the command execution message.
     *
     * @param input String input of user.
     * @return String output of command completion.
     */
    public String run(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
