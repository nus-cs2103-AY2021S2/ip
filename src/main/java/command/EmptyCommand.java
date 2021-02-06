package command;
import oracle.TaskList;
import oracle.Ui;

public class EmptyCommand implements Command {
    /** This is an empty command, it does nothing.
     * @param ui: not needed
     * @param tasks: not needed
     * @return true: tells Oracle whether the loop should be terminated
     */
    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        return true;
    }
}
