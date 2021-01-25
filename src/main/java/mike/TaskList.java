package mike;

import mike.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(List<Task> tasks) {
        this.taskList = new ArrayList();
        this.taskList.addAll(tasks);
    }

    public TaskList() {
        this.taskList = new ArrayList();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task delete(int index) {
        return taskList.remove(index - 1);
    }

    public Task mark(int index) {
        Task taskToMark = taskList.get(index - 1);
        taskToMark.markAsDone();
        return taskToMark;
    }

    public TaskList find(String keyword) {
        TaskList matchingResults = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(keyword)) {
                matchingResults.add(taskList.get(i));
            }
        }
        return matchingResults;
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }
}
