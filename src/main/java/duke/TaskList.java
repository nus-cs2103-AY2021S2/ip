package duke;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    public static List<Task> taskList = new ArrayList<>();

    /*
     * Traverses the task list to find tasks containing a keyword provided by the user.
     *
     * @param keyWord A keyword provided by the user.
     * @return A list of tasks containing the keyword provided by the user.
     */
    public static List<Task> find(String keyWord) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.description.contains(keyWord)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /*
     * Traverses the task list to mark a particular task complete.
     *
     * @param task A task to be marked as complete.
     * @return a string representing the result of the execution of the complete method
     */
    public static String completeTask(Task task) {
        for (Task t: taskList) {
            if(t.equals(task)) {
                t.completeTask();
                return "Task marked complete!\n";
            }
        }
        return "Task not found!";
    }

    /*
     * Traverses the task list to remove a particular task.
     *
     * @param task A task to be removed.
     * @return a string representing the result of the execution of the remove method
     */
    public static void removeTask(Task task) {
        for (Task t: taskList) {
            if (t.equals(task)) {
                taskList.remove(t);
                break;
            }
        }
    }

    /*
     * Traverses the task list to check if a particular task exists.
     *
     * @param task A task to be removed.
     * @return true or false.
     */
    public static boolean contains(Task task) {
        for (Task t: taskList) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }
}
