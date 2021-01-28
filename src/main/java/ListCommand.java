public class ListCommand extends Command {
    /**
     * List out the tasks in the list to user.
     * @param tl task list.
     * @param ui object for user interface.
     * @param storage object to load and save tasks.
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ui.printTasks(tl);
    }
}