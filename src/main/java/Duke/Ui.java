package Duke;

import Duke.Tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class Ui {

    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * To output a line that consists of -
     *
     * @return String that consists of -
     */
    public String showLine() {
        return "------------------------------------------------";
    }

    /**
     * To output an error message
     *
     * @param message the error message in String
     * @return String message
     */
    public String showError(String message) {
        return message;
    }

    /**
     * To indicate that the file is unable to be loaded
     *
     * @return String that states unable to load file
     */
    public String showLoadingError() {
        return "Unable to load file!";
    }

    /**
     * To output a welcome message
     *
     * @return String of welcome message
     */
    public String showWelcome() {
        String introductionMessage = "Hi! I'm Timmy!\nWhat can Timmy note down for you today?";
        String displayFormatMessage = "\nPlease type in any of these format!";
        String displayToDoFormat = "\ntodo [priority][title]";
        String displayEventFormat = "\nevent [priority][title] /at [yyyy-mm-dd] [HH:MM]";
        String displayDeadlineFormat = "\ndeadline [priority][title] /by [yyyy-mm-dd] [HH:MM]";
        String displayListFormat = "\nlist";
        String displayDeleteFormat = "\ndelete [index]";
        String displayDoneFormat = "\ndone [index]";

        return introductionMessage + displayFormatMessage + displayToDoFormat
                + displayEventFormat + displayDeadlineFormat
                + displayListFormat + displayDeleteFormat + displayDoneFormat;
    }

    /**
     * To output the <code>Tasks</code> in a TaskList
     *
     * @param taskList stores the <code>Duke.Tasks.Task</code> in an ArrayList
     * @return String to consists of <code>Duke.Tasks.Task</code> in an ArrayList
     */
    public String printList(TaskList taskList) {
        StringBuilder listStringBuilder = new StringBuilder("Here are the tasks in your list:\n");
        ArrayList<Task> tasks = taskList.getList();

        for (int j = 0; j < tasks.size(); j++) {
            listStringBuilder.append(j + 1).append(".").append(tasks.get(j).toString()).append("\n");
        }

        assert listStringBuilder.length() > 0;
        return listStringBuilder.toString();
    }

    /**
     * To output a message to indicate that the task is marked
     *
     * @param task the <code>Duke.Tasks.Task</code>> to be marked
     * @return String that indicates the <code>Duke.Tasks.Task</code>> to be marked
     */
    public String showMarkTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * To output a message to indicate that the task is deleted
     *
     * @param taskList  stores the <code>Duke.Tasks.Task</code> in an ArrayList
     * @param taskIndex indicates the <code>Duke.Tasks.Task</code> index in the ArrayList
     * @return String that consists of deleted tasks
     */
    public String showDeleteTask(TaskList taskList, int taskIndex) {
        ArrayList<Task> list = taskList.getList();
        int size = list.size();
        String taskName = list.get(taskIndex).toString();
        String displayTaskName = "Ok! I've removed this task:\n" + taskName + "\n";
        String displayLeftoverTasks = "Currently, you have " + (size - 1) + " task(s) in the list!";
        return displayTaskName + displayLeftoverTasks;
    }

    /**
     * To output the added <code>Duke.Tasks.Task</code> to the ArrayList
     *
     * @param taskList stores the <code>Duke.Tasks.Task</code> in an ArrayList
     * @return String that consists of the task added
     */
    public String showAddTask(TaskList taskList) {
        ArrayList<Task> list = taskList.getList();
        int size = list.size();
        String taskName = list.get(size - 1).toString();
        String displayTaskName = "Ok! I've added this task:\n" + taskName;
        String displayLeftoverTasks = "\nCurrently, you have " + size + " task(s) in the list!";
        return displayTaskName + displayLeftoverTasks;
    }
}
