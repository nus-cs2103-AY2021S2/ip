public class CommandDone extends Command {
    private final int index;

    public CommandDone(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Task task = tasks.doneTask(index);
            storage.save(tasks);
            return this.toDukeOutput() + "\n" + task.toString();
        } catch (IndexOutOfBoundsException e) {
            return "Index provided is out of bounds! Please provide a valid index (1 to "
                    + tasks.getTasks().size() + ").";
        }
    }

    @Override
    public String toDukeOutput() {
        return "Impressive, yet another task has been done: ";
    }
}
