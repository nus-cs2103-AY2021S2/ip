package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    public String execute(TaskList tasks, Storage storage) {
        String output = "";
        output += "Here are the tasks in your list:\n";
        int index = 1;
        for(Task task : tasks.getTasks()) {
            output += String.format("%d. %s",index, task.toString());
            output += Ui.showNewLine();
            index ++;
        }
        return output;
    }
}
