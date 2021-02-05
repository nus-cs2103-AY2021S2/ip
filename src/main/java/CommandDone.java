public class CommandDone extends Command {
    private final int index;

    public CommandDone(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task = tasks.doneTask(index);
        storage.save(tasks);
        return this.toDukeOutput() + "\n" + task.toString();
    }

    @Override
    public String toDukeOutput() {
        return "Impressive, yet another task has been done: ";
    }
}
