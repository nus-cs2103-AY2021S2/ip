import java.util.ArrayList;
import java.util.Set;

/**
 * Class that handles the List command, returning the result of all existing entries.
 */

public class ListCommand {

    private TaskList tasks;

    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    public Result execute() {
        String output = "";
        ArrayList<Task> storage = tasks.getStorage();
        if (storage.isEmpty()) {
            output += "Empty List\n";
        } else {
            output += "Here are the tasks.\n";
            for (int j = 0; j < storage.size(); j++) {
                output += (j + 1) + ". "
                        + storage.get(j)
                        + "\n";
            }
        }
        assert output != "" : "output should not be empty.";
        Result result = new Result(output);
        return result;
    }
}
