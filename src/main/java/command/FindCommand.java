package command;

import java.util.ArrayList;
import java.util.List;

import mike.TaskList;
import task.Task;

public class FindCommand implements Command {
    private String keyword;
    private TaskList taskList;
    private List<Task> foundTasks = new ArrayList<>();

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public TaskList runCommand(TaskList taskList) {
        Task tempTask;
        for (int i = 1; i <= taskList.getNumTasks(); i++) {
            tempTask = taskList.getNthTask(i);
            if (tempTask.getTaskDescription().contains(this.keyword)) {
                this.foundTasks.add(tempTask);
            }
        }
        return taskList;
    }

    @Override
    public String getResponse() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Here are the matching tasks in your list: \n");
        for (int i = 1; i <= this.foundTasks.size(); i++) {
            buffer.append(i);
            buffer.append(". ");
            buffer.append(this.foundTasks.get(i - 1).toString());
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
