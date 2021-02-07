import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Initialise Task List with new arrayList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Initialise Task List with existing taskList.
     *
     * @param taskList The task list that was loaded from Storage.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Get size of taskList.
     *
     * @return Size of task list.
     */
    public int getSize() {
        if (taskList.isEmpty()) {
            return 0;
        } else {
            return taskList.size();
        }
    }

    /**
     * Get task List.
     *
     * @return Task List.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Add task to task List.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    public void delete(int itemNum) {
        taskList.remove(itemNum);
    }

    /**
     * Search through all the tasks to see which ones contains the given keyword.
     *
     * @param keyword The keyword to be searched.
     * @return The task list that contains only the relevant tasks.
     */
    public ArrayList<Task> search(String keyword) {
        ArrayList<Task> taskListToPrint = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                taskListToPrint.add(task);
            }
        }
        return taskListToPrint;
    }

    /**
     * A toString method to display all the tasks in the task list.
     *
     * @return A string containing all the tasks in the task list.
     */
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
