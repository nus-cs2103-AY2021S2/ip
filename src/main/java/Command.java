public class Command {
    protected TaskList tasks;

    public Command() {}

    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }

    public CommandResult execute() throws DukeException {
        return new CommandResult();
    }
}
