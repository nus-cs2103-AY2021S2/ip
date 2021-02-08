public abstract class Command {
    public abstract String excute(TaskList tasks, Ui ui);

    public boolean isExit() {
        return false;
    }
}
