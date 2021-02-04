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
    public static List<Task> find(String keyWord){
        List<Task> foundTasks = new ArrayList<>();
        for(Task t : taskList){
            if(t.description.contains(keyWord)){
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }

    /*
     * Traverses the task list to mark a particular task complete.
     *
     * @param task A task to be marked as complete.
     */
    public static void completeTask(Task task){
        for(Task t: taskList){
            if(t.equals(task)){
                t.completeTask();
            }
        }
    }

    /*
     * Traverses the task list to remove a particular task.
     *
     * @param task A task to be removed.
     */
    public static void removeTask(Task task){
        for(Task t: taskList){
            if(t.equals(task)){
                taskList.remove(t);
                break;
            }
        }
    }
}
