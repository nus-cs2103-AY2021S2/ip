public class CommandSort extends Command {
    private final String sortBy;

    public CommandSort(String sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        boolean isSorted = tasks.sortTasks(sortBy);
        if (isSorted) {
            storage.save(tasks);
            StringBuilder sortedTasks = new StringBuilder(toDukeOutput()).append(sortBy).append("!\n");
            sortedTasks.append(tasks.printTasks());

            return sortedTasks.toString();
        } else {
            return "Invalid sort key, task remains unsorted.";
        }
    }

    @Override
    public String toDukeOutput() {
        return "Task sorted by: ";
    }
}
