package fakebot.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> tasks;
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int i) {
        tasks.remove(i);
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Find all task that contain search string
     * @param search String to search
     * @return Return list of task that contain search string
     */
    public List<Task> find(String search) {
        List<Task> foundTask = new ArrayList<>();
        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getTaskName().contains(search)) {
                foundTask.add(tasks.get(i));
            }
        }
        return foundTask;
    }
}
