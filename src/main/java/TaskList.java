import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Initializes Task List with new taskList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Initializes Task List with existing taskList
     * @param taskList The task list that was loaded from Storage
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns Size of taskList
     * @return Size of task list
     */
    public int getSize() {
        if (taskList.isEmpty()) {
            return 0;
        } else {
            return taskList.size();
        }
    }

    /**
     * Gets Task List
     * @return Task List
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Add Task to Task List
     * @param task The task to be added
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Searches through all the tasks to see which ones contains the given keyword
     * @param keyword The keyword to be searched
     * @return The task list that contains only the relevant tasks
     */
    public ArrayList<Task> search(String keyword) {
        ArrayList<Task>taskListToPrint = new ArrayList<>();
        for (Task task: taskList) {
            if (task.toString().contains(keyword)) {
                taskListToPrint.add(task);
            }
        }
        return taskListToPrint;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            if (i == taskList.size()) {
                result.append(i).append(". ").append(taskList.get(i - 1));
            } else {
                result.append(i).append(". ").append(taskList.get(i - 1)).append("\n");
            }
        }
        return result.toString();
    }
}
