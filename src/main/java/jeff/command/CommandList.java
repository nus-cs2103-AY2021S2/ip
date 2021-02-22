package jeff.command;

import jeff.JeffException;
import jeff.Storage;
import jeff.TaskList;

public class CommandList extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        if (tasks.getNumTasks() == 0) {
            return "No tasks right now";
        }
        String toPrint = "";
        for (int i = 0; i < tasks.getNumTasks(); i++) {
            toPrint += (i + 1) + ". " + tasks.getTask(i) + "\n";
        }
        return toPrint;
    }
}
