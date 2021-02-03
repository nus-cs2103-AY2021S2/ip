package Command;
import Oracle.TaskList;
import Oracle.Ui;
import Entry.Todo;

public class TodoCommand implements Command{
    private final String taskDescription;

    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        tasks.add(new Todo(this.taskDescription));
        ui.showNewTask(tasks.size(), tasks.get(tasks.size()-1));
        return true;
    }
}
