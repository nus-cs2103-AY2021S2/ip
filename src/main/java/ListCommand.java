/**
 * Command to list out all the tasks at hand.
 * Inherits from the superclass Command.
 */
public class ListCommand extends Command {
    private final String type;

    public ListCommand() {
        this.type = "all";
    }

    public ListCommand(String type) {
        this.type = type;
    }

    /**
     * Uses the Ui class to print out all the tasks that TaskList is keeping track of.
     * @param taskList TaskList that contains all tasks at hand.
     * @param ui Ui that deals with interaction with the user.
     * @param storage Storage that deals with storing TaskList into hard drive.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (this.type.equals("normal")) {
            return ui.printTasksNormal(taskList);
        } else if (this.type.equals("snoozed")) {
            return ui.printTasksSnoozed(taskList);
        } else if (this.type.equals("all")) {
            return ui.printTasksAll(taskList);
        } else {
            throw new DukeException("Unknown list command.");
        }
    }
}
