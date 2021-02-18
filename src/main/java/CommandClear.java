public class CommandClear extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.clearAllTasks();
        storage.save(tasks);

        return toDukeOutput();
    }

    @Override
    public String toDukeOutput() {
        return "Clear complete, the task list is now empty.";
    }
}
