import java.util.ArrayList;
import java.util.List;

public class TaskList {

    public List<Task> taskList;

    TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Prints the statements showing this task has been added to list.
     * @param currentTask Current task.
     */
    protected String logTask(Task currentTask) {

        return String.format("Got it. I've added this task:" + currentTask +
                String.format("\nNow you have %d tasks in the list", taskList.size()));

    }


    protected String logAllTasks() {
        StringBuilder out = new StringBuilder();
        for (Task eachTask : taskList) {
            String taskType = eachTask.getTaskType();
            out.append(taskType).append("|");
            out.append(eachTask.getStatusIcon()).append("|");
            if (taskType.equals("D") || taskType.equals("E")) {
                out.append(eachTask.getDescription()).append("|");
                out.append(eachTask.getEventDate());
            } else  {
                out.append(eachTask.getDescription());
            }
            out.append("\n");
        }
        return out.toString();
    }


    /**
     * Adds current task to list of tasks.
     * @param currentTask Current task.
     */
    protected void addToTasks(Task currentTask) {
        taskList.add(currentTask);
    }

    /**
     * Enumerates all tasks in the list using 1-based indexing.
     */
    protected String enumerateTasks() {

        String res = "Here are the tasks in your list:";

        int counter = 1;
        for (Task eachTask : taskList) {
            res += String.format("\n%d. %s", counter, eachTask);
            counter++;
        }
        return res;
    }

    /**
     * Marks task as done based on 1-based indexing.
     * @param index Given index of task
     */
    protected String markAsDone(int index) {
        // Retrieving task
        Task givenTask = taskList.get(index - 1);
        givenTask.markAsDone();
        String res = "Nice! I've marked this task as done:";

        res += String.format("\n  [%s][%s] %s",
                givenTask.getTaskType(), givenTask.getStatusIcon(),
                givenTask.getDescription());
        return res;
    }

    /**
     * Removes respective task in the list (1-based indexing).
     * @param index Index of task to remove
     */
    protected String removeTask(int index) {
        Task removedTask = taskList.remove(index - 1);

        String res = "Noted. I've removed this task:" + "  " + removedTask;

        res += String.format("Now you have %d tasks in your list.",
                taskList.size());
        return res;
    }

    protected String retrieveByKeyword(String keyword) {
        int counter = 1;

        String res = "Here are the matching tasks in your list:";

        for (Task eachTask : taskList) {
            if(eachTask.getDescription().contains(keyword)) {
                res += String.format("\n%d. %s", counter, eachTask);
                counter++;
            }
        }
        return res;
    }

}
