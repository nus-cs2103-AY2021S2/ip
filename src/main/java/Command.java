public abstract class Command {
    protected String[] details;
    protected Input taskType;

    public Command() {}

    public Command(String[] details) {
        this.details = details;
    }

    public abstract void execute (TaskList taskList, Ui ui, Storage storage);
}
