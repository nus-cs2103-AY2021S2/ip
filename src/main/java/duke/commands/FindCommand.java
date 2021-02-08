package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String description;

    public FindCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui) {
        TaskList matchingTasks = new TaskList();

        for (Task task : tasks.getListOfTasks()) {
            String taskDescription = task.getDescription().toLowerCase();
            String stringToFind = this.description.toLowerCase();

            if (taskDescription.contains(stringToFind)) {
                matchingTasks.addTask(task);
            }
        }

        ui.handleFind(matchingTasks);
    }

    public boolean isExit() {
        return false;
    }
}
