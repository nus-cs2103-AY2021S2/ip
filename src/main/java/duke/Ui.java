package duke;

import java.util.ArrayList;


/**
 * User interface class for Duke's interaction with users, send messages etc.
 */
public class Ui {
    protected Statistics stats;


    /**
     * Constructor for Ui class.
     */
    public Ui() {
        this.stats = new Statistics();
    }

    /**
     * Prints greetings message at the start when user runs the Duke app.
     */
    public static String greetings() {
        String message = "Eren... I have waited to meet you for a long time.\n"
                + "------\n"
                + "How can Zeke assist you?\n"
                + "Type \"help\" for the list of commands!";
        return message;
    }

    /**
     * Prints exit message when user inputs "bye".
     */
    public String exit() {
        String message = "Bye Eren. Till next time.";
        return message;
    }

    /**
     * Prints message when user adds a task to list
     *
     * @param list list of tasks
     * @param addedTask task that is added
     */
    public String addTaskMessage(ArrayList<Task> list, Task addedTask) {
        String message = "Got it. I've added this task:\n"
                + addedTask + "\n"
                + "Now you have " + list.size() + " tasks in the list.";
        return message;
    }

    /**
     * Prints message when user deletes a task from list
     *
     * @param list list of tasks
     * @param deletedTask task that is deleted
     */
    public String deleteTaskMessage(ArrayList<Task> list, Task deletedTask) {
        String message = "Noted. I've removed this task:\n"
                + deletedTask + "\n"
                + "Now you have " + list.size() + " tasks in the list.";
        return message;
    }

    /**
     * Prints message when user checks a task as done
     *
     * @param task task that is checked as done
     */
    public String checkAsDoneMessage(Task task) {
        String message = "Nice! I've marked this task as done:\n" + task;
        return message;
    }

    /**
     * Prints message when user requests for tasks in list
     *
     * @param list list of tasks
     */
    public String listAllTasks(ArrayList<Task> list) {
        String message = "Here are the tasks in your list:\n";
        int num = 1;
        for (Task task : list) {
            message += String.format("%d. %s", num, task) + "\n";
            num++;
        }
        return message;
    }

    /**
     * Prints matching tasks in list
     *
     * @param keyword keyword to search for in list
     * @param list list of tasks
     */
    public String findTask(String keyword, ArrayList<Task> list) {
        ArrayList<Task> newList = new ArrayList<>();
        String message = "Here are the matching tasks in your list:\n";
        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                newList.add(task);
            }
        }
        if (newList.size() == 0) {
            message += "No matching tasks found. " + new String(Character.toChars(0x1F648));
        } else {
            for (Task task : newList) {
                if (task.getDescription().contains(keyword)) {
                    message += task + "\n";
                }
            }
        }
        return message;
    }

    /**
     * Prints the help message
     *
     * @return help message as a String
     */
    public String viewHelpMessage() {
        String message = "List of commands\n"
                + "bye -> Exit application\n"
                + "list -> List all the tasks you currently have\n"
                + "done [index] -> Mark a task as completed \n"
                + "delete [index] -> Delete a task\n"
                + "find [keyword] -> Search for the keyword in the list of tasks\n"
                + "todo [description] -> Add a todo\n"
                + "deadline [description] /by [date] -> Add a deadline\n"
                + "event [description] /at [date] -> Add an event\n"
                + "stats -> Show overdue tasks and tasks happening soon";
        return message;
    }

    /**
     * Prints statistics for the task list
     *
     * @param list task list
     * @return statistics as a String
     */
    public String getStatistics(ArrayList<Task> list) {
        String divider = "------\n";
        String message = "";
        message += "Overdue Deadlines:\n";
        message += stats.getOverdueDeadlines(list);
        message += divider;
        message += "Deadlines and Events due/happening in a week:\n";
        message += stats.getTasksDueSoon(list);
        return message;
    }
}
