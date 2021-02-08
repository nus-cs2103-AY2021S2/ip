public interface Command {
    public boolean isExit();

    public void execute(TaskList tasks, Ui ui, Storage storage);
}
