package command;
import oracle.TaskList;
import oracle.Ui;

public class ExitCommand implements Command{
    /** Terminating command called when user exits the program
     * @param ui let the user know that we are exiting
     * @param tasks not needed
     * @return false, terminating the while loop
     */
    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        ui.showGoodbye();
        return false;
    }
}
