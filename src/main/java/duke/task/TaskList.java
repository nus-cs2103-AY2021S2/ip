package duke.task;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Represents a list of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds task to list
     * 

     * @param task Task to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Gets the task in the list based on its task number
     * 
     * @param taskNum Task number of the task to be found
     * @return Task with the given task number
     */
    public Task get(int taskNum) {
        return this.tasks.get(taskNum - 1);
    }

    /**
     * Marks the task with the given task number as complete
     * 
     * @param taskNum Task number of the task to be marked as complete
     */
    public void done(int taskNum) {
        this.tasks.get(taskNum - 1).setCompletion(true);
    }

    /**
     * Removes the task with the given task number from the list
     * 
     * @param taskNum Task number of the task to be removed
     */
    public void delete(int taskNum) {
        this.tasks.remove(taskNum - 1);
    }

    /**
     * Gets the total number of tasks currently in the list
     * 
     * @return Number of tasks in the list
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Filters tasks that are associated with a specified date
     * 
     * @param date Date to filter for
     * @return Filtered task list
     */
    public TaskList getTasksOnDate(LocalDate date) {
        TaskList tasksOnDate = new TaskList();
        for (int i = 1; i <= this.size(); i++) {
            Task currTask = this.get(i);
            if (date.equals(currTask.getDate())) {
                tasksOnDate.add(currTask);
            }
        }
        return tasksOnDate;
    }

    @Override
    public String toString() {
        StringBuilder taskListString = new StringBuilder();
        for (int i = 1; i <= this.tasks.size(); i++) {
            taskListString.append(i + "." + this.get(i).toString() + "\n");
        }
        return taskListString.toString();
    }

}
