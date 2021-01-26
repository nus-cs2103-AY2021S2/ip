public abstract class Command {
    public abstract void excute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
