package duke.command;

import duke.TaskList;
import duke.task.Task;

public class FindCommand implements ICommand {
    private TaskList tasks;

    public FindCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public void execute(String parameters) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(listToString(parameters.strip()));
    }

    private String listToString(String keyword) {
        String content = "";
        Integer count = 1;
        for (Task t: tasks.findTasksWithString(keyword)) {
            content += count.toString() + ".";
            content += t.toString();
            content += "\n";
            count++;
        }
        return content.trim();
    }
}
