package duke.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void deleteTask(int index) {
        tasks.remove(index - 1);
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public int getTasksSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Returns a TaskList containing tasks that have the keyword String in their descriptions.
     * @param keyword Keyword String to find relevant tasks
     * @return TaskList containing tasks that have keyword String in their descriptions
     */
    public TaskList find(String keyword) {
        TaskList results = new TaskList();
        keyword = keyword.toLowerCase();
        for (Task t : tasks) {
            String description = t.getDescription().toLowerCase();
            if (description.contains(keyword)) {
                results.addTask(t);
            }
        }
        return results;
    }
}
