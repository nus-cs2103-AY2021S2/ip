public class CommandDone extends Command {
    private final int index;

    public CommandDone(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.doneTask(index);
        storage.save(tasks);
        ui.printCommand(this);
        ui.printTask(task);
    }

    @Override
    public String toDukeOutput() {
        return "Impressive, yet another task has been done: ";
    }
}
