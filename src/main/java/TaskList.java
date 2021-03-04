import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor method.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor method
     * @param task ArrayList<Task>
     */
    public TaskList(ArrayList<Task> task) {
        this.tasks = task;
    }

    /**
     * Method to get TaskList
     * @return the ArrayList<Task> of TaskList
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }


    /**
     * Method to get the Task
     * @param index of the Task in the TaskList
     * @return the Task object at the index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }
    /**
     * Method to add the Task
     * @param task to be added
     * @return TaskList with task added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
    /**
     * Method to delete the Task
     * @param index of the Task in the TaskList
     * @return Task object that was deleted
     */
    public Task deleteTask(int index) {
        Task deletedTask = getTask(index);
        tasks.remove(index);
        return deletedTask;
    }
    /**
     * Method to check for duplicates in the TaskList
     * @return delete the duplicated task in the TaskList
     */
    public void checkForDuplicate() {
        ArrayList<Task> list = new ArrayList<>();
        for (Task check: tasks) {
            if (!list.contains(check)) {
                list.add(check);
            }
        }
        tasks.clear();
        tasks.addAll(list);
    }
    /**
     * Method to get the size of TaskList
     * @return the size of TaskList
     */
    public int numOfTasks() {
        return tasks.size();
    }
}


