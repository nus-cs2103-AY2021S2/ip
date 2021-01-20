import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;
    private int numTasks = 0;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Add task to list of tasks then increment number of tasks stored
     * @param task task to be added to list
     */
    public void addTaskToList(Task task) {
        taskList.add(task);
        numTasks++;
    }

    /**
     * Get number of tasks stored in task list
     * @return num of tasks in list
     */
    public int getNumTasks() {
        return numTasks;
    }

    /**
     * return nth task starting at index 1
     * @param i the index of the task to be retrieved
     * @return the Task object
     */
    public Task getNthTask(int i){
        return this.taskList.get(i-1);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        for (int i = 1; i <= numTasks; i++) {
            buffer.append(i);
            buffer.append(". ");
            buffer.append(taskList.get(i-1).toString());
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
