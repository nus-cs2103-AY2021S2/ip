package duke.commands;

import duke.tasks.TaskList;

public class FindCommand extends Command {
    private TaskList taskList;
    private String taskDescription;

    public FindCommand(TaskList taskList, String taskDescription) {
        super(taskList);
        this.taskDescription = taskDescription;
    }

    public TaskList execute() {
        return this.getTaskList();
    }

    public String toString() {
        String message = "Here are the matching tasks in your list:\n";
        TaskList tasks = this.getTaskList();
        boolean first = true;
        for (int i = 0; i < tasks.size(); ++i) {
            if (tasks.get(i).getDescription().contains(this.taskDescription)) {
                if (first) {
                    message += (i + 1) + "." + tasks.get(i).toString();
                    first = false;
                } else {
                    message += "\n" + (i + 1) + "." + tasks.get(i).toString();
                }
            }
        }
        if (first) {
            message = "There are no matching tasks";
        }
        return message;
    }
}