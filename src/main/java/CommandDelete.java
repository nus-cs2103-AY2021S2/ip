public class CommandDelete extends Command {
    private final int index;

    public CommandDelete (int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task = tasks.deleteTask(index);
        storage.save(tasks);
        return this.toDukeOutput() + "\n" + task.toString();
    }

    @Override
    public String toDukeOutput() {
        return "Alrighty bossman. I shall wipe this task off the face of the earth: ";
    }
}
