public class ListCommand extends Command {

    public ListCommand() {
        this.type = "list";
        this.description = "";
        this.isExit = false;
    }

    /**
     * Prints tasks in task list.
     *
     * @param tasks Task list.
     * @param ui User interface.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTaskList();
    }

    @Override
    public boolean isExit() {
        return this.isExit;
    }
}
