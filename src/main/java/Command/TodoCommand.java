package Command;
import Oracle.TaskList;
import Oracle.Ui;
import Entry.Todo;

public class TodoCommand implements Command{
    private final String taskDescription;

    /**
     * @param taskDescription description of the todo
     */
    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /** Creates a new Todo
     * @param ui helper to interact with user
     * @param tasks we add the new created todo here
     * @return true
     */
    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        tasks.add(new Todo(this.taskDescription));
        ui.showNewTask(tasks.size(), tasks.get(tasks.size()-1));
        return true;
    }
}
