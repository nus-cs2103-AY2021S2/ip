abstract class Command {
    protected String command;
    public Command(String command) {
        this.command = command;
    }
    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws Exception;

    public abstract boolean isExit();
}
