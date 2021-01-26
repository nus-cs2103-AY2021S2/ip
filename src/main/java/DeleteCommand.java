public class DeleteCommand extends IndexCommand {
    DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = getIndex();
        if (index < tasks.size()) {
            Task removingTask = tasks.get(index);
            tasks.remove(index);
            storage.updateInFile(tasks);
            System.out.println(" Following task is removed:");
            System.out.println("  " + removingTask);
            System.out.println(" Now you have " + tasks.size() + " tasks.");
        } else {
            throw new TaskIndexOutOfBoundException("There is no task numbered " + (index + 1) + "!");
        }

    }
}
