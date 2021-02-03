public abstract class Command {
    public abstract String excute(TaskList tasks);

    public boolean isExit() {
        return false;
    }
}
