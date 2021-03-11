package duke;

public abstract class Command {
    protected final String command;
    protected final String input;
    protected TaskList taskList;
    protected Ui ui;
    protected Parser parser;

    public Command(String command, String input, TaskList taskList) {
        this.command = command;
        this.input = input;
        this.taskList = taskList;
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Returns the task list.
     *
     * @return Task list.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Executes the command based on the user input and task list.
     */
    public abstract String execute();
}
