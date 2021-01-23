public abstract class Command {
    public abstract void execute(TaskList tasks, String input, DataHandler dataHandler);
    public abstract boolean isExit();
}
