package duke;

import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getIndex(int i) {
        return taskList.get(i);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void removeIndex(int i) {
        this.taskList.remove(i);
    }

    public TaskList find(String keyword) {
        TaskList toReturn = new TaskList();
        for (Task t : this.taskList) {
            if (t.getTaskName().contains(keyword)) {
                toReturn.add(t);
            }
        }
        return toReturn;
    }
}
