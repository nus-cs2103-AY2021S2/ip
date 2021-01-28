public class CommandDelete extends Command {
    private final int index;

    public CommandDelete (int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.deleteTask(index);
        storage.save(tasks);
        ui.printCommand(this);
        ui.printTask(task);
    }

    @Override
    public String toDukeOutput() {
        return "Alrighty bossman. I shall wipe this task off the face of the earth: ";
    }
}
