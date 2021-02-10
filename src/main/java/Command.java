public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui);

    public boolean isExit() {
        return false;
    }
}
