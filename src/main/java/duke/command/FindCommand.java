package duke.command;

import duke.TaskList;
import duke.TaskStorage;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public boolean execute(TaskList tasks, Ui ui, TaskStorage storage) {
        List<Task> temp = new ArrayList<>();
        TaskList allTasks = storage.retrieveData();
        for (int i = 0; i < allTasks.getSize(); i++) {
            Task task = allTasks.getTask(i);
            String description = task.getDescription();
            if (description.contains(keyword)) {
                temp.add(task);
            }
        }
        if (temp.isEmpty()) {
            ui.print("There are no tasks with such keyword!");
        } else {
            TaskList matchingTasks = new TaskList(temp);
            ui.print(matchingTasks);
        }
        return true;
    }
}
