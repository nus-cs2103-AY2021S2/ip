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


    /**
     * Method that logs all existing tasks and returns a string that can be logged
     * into the local disk in data/Duke.txt.
     * @return A string containing all existing tasks.
     */
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
     * @return String representing all existing tasks with 1-based indexing
     */
    protected String enumerateTasks() {

        StringBuilder res = new StringBuilder("Here are the tasks in your list:");

        int counter = 1;
        for (Task eachTask : taskList) {
            res.append(String.format("\n%d. %s", counter, eachTask));
            counter++;
        }
        assert counter == taskList.size();
        return res.toString();
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

        String res = "Noted. I've removed this task:\n" + "  " + removedTask;

        res += String.format("\nNow you have %d tasks in your list.",
                taskList.size());
        return res;
    }

    /**
     * Retrieve task(s) by keyword match so long as description contains the word.
     * @param command Keyword to match
     * @return A string line-spaced by each task that has the keyword
     */
    protected String retrieveByKeyword(String command) {
        int counter = 1;
        String[] keywordSearch = command.split("\\s+");
        assert keywordSearch.length >=2;
        String keyword = keywordSearch[1];
        String res = "Here are the matching tasks in your list:";
        int lenSearch = keywordSearch.length;
        for (Task eachTask : taskList) {
            if (lenSearch == 2) {
                // default partial search case sensitive
                if (eachTask.getDescription().contains(keyword)) {
                    res += String.format("\n%d. %s", counter, eachTask);
                    counter++;
                }
            } else if (lenSearch == 3) {
                String searchType = keywordSearch[2];
                if (searchType.equalsIgnoreCase("p")) {
                    if (eachTask.getDescription().contains(keyword)) {
                        res += String.format("\n%d. %s", counter, eachTask);
                        counter++;
                    }
                } else if (searchType.equalsIgnoreCase("pi")) {
                    // Case insensitive partial search
                    if (eachTask.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                        res += String.format("\n%d. %s", counter, eachTask);
                        counter++;
                    }
                } else if (searchType.equalsIgnoreCase("f")) {
                    // full search must be exactly as the task description
                    if (eachTask.getDescription().equals(keyword)) {
                        res += String.format("\n%d. %s", counter, eachTask);
                        counter++;
                    }
                }
            }

        }
        return res;
    }

}
