package duke;

/**
 * Command for duke to list out all the tasks.
 */
public class ListCommand extends Command {

    /**
     * Lists out the tasks in the list to user.
     *
     * @param tl task list.
     * @param ui object for user interface.
     * @param storage object to load and save tasks.
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        return ui.printTasks(tl);
    }
}
