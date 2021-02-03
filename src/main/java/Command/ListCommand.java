package Command;
import Oracle.TaskList;
import Oracle.Ui;

public class ListCommand implements Command{
    /**
     * @param ui helper print the tasks
     * @param tasks we pull the tasks from here
     * @return true
     */
    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        ui.showList(tasks.getTasks());
        return true;
    }
}
