package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (Task t : taskList.getTasks()) {
            if (t.getName().contains(this.keyword)) {
                tasks.add(t);
            }
        }
        ui.showFound(tasks);
    }
}
