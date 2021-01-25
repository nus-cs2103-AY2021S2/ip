package duke.command;

import duke.task.Task;
import duke.task.TaskList;

public class ListCmd extends Command {

    @Override
    public String execute(TaskList lst) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lst.size(); i++) {
            Task task = lst.get(i);
            sb.append(String.format("%d. %s\n", i + 1, task.toString()));
        }
        return sb.toString();
    }
}
