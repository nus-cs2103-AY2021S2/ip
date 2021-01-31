package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskManager;
import duke.task.Task;

public class ListCommand extends Command {

    /**
     *  ListCommand constructor.
     */
    public ListCommand() {
        //do nothing
    }

    /**
     *  Executes ListCommand.
     *
     *  @param tm TaskManager Object from Duke.
     *  @param st Storage Object from Duke.
     */
    public String execute(TaskManager tm, Storage st) {
        ArrayList<Task> tasks = tm.getTasks();
        StringBuilder res = new StringBuilder("Listing all matching tasks: \n");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            res.append(num)
                    .append(": ")
                    .append(tasks.get(i))
                    .append("\n");
        }

        if (tasks.size() == 0) {
            res.append("--- No Tasks Found ---");
        }
        return res.toString();
    }
}
