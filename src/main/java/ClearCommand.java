import java.util.ArrayList;
import java.util.Set;

/**
 * Class that handles the Clear command, emptying the
 * storage list of things to do.
 */
public class ClearCommand {

    private TaskList tasks;

    public ClearCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    public Result execute() {
        ArrayList<Task> storage = tasks.getStorage();
        Set<Task> setOfTasks = tasks.getDuplicateChecker();
        storage.clear();
        setOfTasks.clear();
        Result result = new Result("List has been cleared.");
        return result;
    }
}