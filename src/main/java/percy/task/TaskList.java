package percy.task;

import java.util.ArrayList;

/**
 * A list of tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Instantiates a TaskList object with an empty ArrayList.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Instantiates a TaskList object with an ArrayList of Task (given as an argument).
     *
     * @param list an ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns the ArrayList of Tasks of this TaskList.
     *
     * @return The ArrayList of Tasks of this TaskList.
     */
    public ArrayList<Task> getTaskList() {
        return this.list;
    }

    /**
     * Adds task to TaskList.
     * @param task the task to be added.
     */
    public void addTaskToList(Task task) {
        list.add(task);
    }

    /**
     * Deletes task from TaskList.
     * @param task the task to be deleted.
     */
    public void deleteTaskFromList(Task task) {
        list.remove(task);
    }

    /**
     * Filters the task list with any task containing keywords given.
     * @param keywords the find keyword
     * @return filtered task list
     */
    public ArrayList<Task> findTask(String... keywords) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task t : list) {
            for (String s : keywords) {
                if (t.getDescription().toLowerCase().contains(s)) {
                    if (!filteredTasks.contains(t)) {
                        filteredTasks.add(t);
                    }
                }
            }
        }
        return filteredTasks;
    }
}
