package command;
import oracle.TaskList;
import oracle.Ui;

public class ListCommand implements Command {
    /**
     * @param ui: helper print the tasks
     * @param tasks: we pull the tasks from here
     * @return true: tells Oracle whether the loop should be terminated
     */
    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        ui.showList(tasks.getTasks());
        return true;
    }
}
