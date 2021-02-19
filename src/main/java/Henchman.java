public class Henchman {
    private final Storage STORAGE;
    private final TaskList TASKS;

    /**
     * Constructs Henchman object, which immediately attempts to load previously save tasks (if save file exists).
     */
    public Henchman() {
        STORAGE = new Storage();

        TaskList tempTasks;
        try {
            tempTasks = new TaskList(STORAGE.load());
        } catch (HenchmanException e) {
            tempTasks = new TaskList(); // case where save file does not exist
        }

        assert tempTasks != null : "Error: Attempt to construct Henchman without valid Storage.";
        this.TASKS = tempTasks;
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
            return command.execute(TASKS, STORAGE);
        } catch (HenchmanException e) {
            return e.getMessage();
        }
    }
}
