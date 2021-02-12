public abstract class Command {
    protected String input;
    protected String[] parts;
    protected TaskList tasks;

    Command (String input, String[] parts, TaskList tasks) {
        this.input = input;
        this.parts = parts;
        this.tasks = tasks;
    }

    /**
     * Returns a string representation of the Task after the execution of a process.
     * The process is determined by the user input.
     *
     * @return A string representation of task.
     * @throws InsufficientArgumentsException If no arguments are provided.
     */
    public abstract String execute() throws InsufficientArgumentsException;
}
