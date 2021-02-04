/**
 * Command to list out all the tasks at hand.
 * Inherits from the superclass Command.
 */
public class ListCommand extends Command {
    /**
     * Uses the Ui class to print out all the tasks that TaskList is keeping track of.
     * @param taskList TaskList that contains all tasks at hand.
     * @param ui Ui that deals with interaction with the user.
     * @param storage Storage that deals with storing TaskList into hard drive.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printTasks(taskList);
    }
}
