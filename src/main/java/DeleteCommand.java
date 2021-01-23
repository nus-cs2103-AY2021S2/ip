public class DeleteCommand extends ModificationCommand {
    protected int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Delete a task by its index
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the file writer
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.remove(index);
        super.execute(tasks, ui, storage);

        ui.printLines(
                "Poof! This task is gone:",
                "\t" + deletedTask.toString(),
                getTasksLeftString(tasks));
    }
}
