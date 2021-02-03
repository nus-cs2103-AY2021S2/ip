package Command;
import Oracle.TaskList;
import Oracle.Ui;

public class EmptyCommand implements Command{
    /** This is an empty command, it does nothing.
     * @param ui not needed
     * @param tasks not needed
     * @return true
     */
    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        return true;
    }
}
