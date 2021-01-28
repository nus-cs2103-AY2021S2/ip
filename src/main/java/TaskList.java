import java.util.ArrayList;

/**
 * Represents a list of task
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor of TaskList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor of TaskList
     * @param taskList A list of task
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Update the status of one of the tasks in the list
     * @param index The index of the task to be updated
     * @param done The status of the task to be updated
     * @return An updated Task object containing the changed task.
     */
    public Task changeStatus(int index, boolean done) {
        Task updatedTask = this.taskList.get(index);
        updatedTask.status(done);
        return updatedTask;
    }

    /**
     * Returns the list of tasks
     * @return ArrayList of the tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the total number of tasks in the list
     * @return The size of the ArrayList
     */
    public int getSize() {
        return this.taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Adds a todo task to the list.
     * @param task Todo task to be added
     */
    public void addToDo(Todo task) {
        taskList.add(task);
    }

    /**
     * Adds an event to the list.
     * @param task Event to be added
     */
    public void addEvent(Event task) {
        taskList.add(task);
    }

    /**
     * Add a deadline to the list
     * @param task The deadline to be added
     */
    public void addDeadline(Deadline task) {
        taskList.add(task);
    }

    /**
     * Remove a task from the list
     * @param index The index of the task to be removed
     * @return The updated Task object with the task removed.
     */
    public Task removeTask(int index) {
        return taskList.remove(index);
    }

    /**
     * Prints the whole list of tasks
     * @return A string representing the Task.
     */
    @Override
    public String toString() {
        StringBuilder task = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            task.append(index).append(".").append(taskList.get(i)).append("\n");
        }
        return task.toString();
    }
}
