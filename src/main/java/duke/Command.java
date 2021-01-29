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

    public TaskList getTaskList() {
        return this.taskList;
    }

    public abstract void execute();
}
