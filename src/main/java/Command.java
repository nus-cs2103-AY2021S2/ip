public abstract class Command {
    public abstract CommandResult execute(TaskList tasks, Storage storage);
}
