package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class Ui helps Danh's Duke interact with user by calling suitable method.
 * Ui has 2 main functions: read input and return output with print.
 */
class Ui {
    private final Scanner input;

    /**
     * Returns an Ui with integrated Scanner.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Read a command line entered by user and return it.
     *
     * @return the command line entered by user.
     */
    public String readCommand() {
        return input.nextLine();
    }

    /**
     * Check if user still enter command line or not.
     *
     * @return answer in form of boolean
     */
    public boolean stillHaveCommand() {
        return input.hasNextLine();
    }

    /**
     * Say Bye
     */
    public String echoBye() {
        String ans = "Bye. Hope to see you again soon!\n";
        return ans;
    }

    /**
     * Say Hi!
     */
    public String echoHi() {
        String ans = " Hello! I'm Danh's Duke\nWhat can I do for you, Mr Danh?\n";
        return ans;
    }

    /**
     * Print all the tasks in taskList.
     *
     * @param taskList TaskList related.
     */
    public String echoPrintList(ArrayList<Task> taskList) {
        String ans = "Here are the tasks in your list:\n";
        int index = 1;
        for (Task task : taskList) {
            ans += String.format(" %d. " + task.printTask() + "\n", index);
            index++;
        }
        return ans;
    }

    /**
     * Print the results of adding a task to taskList.
     *
     * @param task      The task added
     * @param noOfTasks Number of tasks in taskList after adding.
     */
    public String echoAddToList(Task task, int noOfTasks) {
        String ans = "Got it. I've added this task: \n" + " ";
        ans += task.printTask() + "\n" + String.format("Now you have %d tasks in the list.\n", noOfTasks);
        return ans;
    }

    /**
     * Print the results of marking a Task as done.
     *
     * @param task Task done.
     */
    public String echoMarkTaskDone(Task task) {
        String ans = "Nice! I've marked this task as done: \n" + " " + task.printTask() + "\n";
        return ans;
    }

    /**
     * Print the results of deleting a Task.
     *
     * @param task Task deleted.
     */
    public String echoDeleteTask(Task task) {
        String ans = "Noted. I've removed this task: \n" + " " + task.printTask() + "\n";
        return ans;
    }

    /**
     * Print the error message of a DukeException.
     *
     * @param err DukeException object related.
     */

    public String echoErrMsg(DukeException err) {
        String ans = err.getMessage();
        return ans;
    }

    /**
     * Print all the tasks of a specific day.
     *
     * @param taskList The tasklist related.
     * @param dateTime The day that we want to search for.
     */
    public String echoTaskThisDay(ArrayList<Task> taskList, LocalDateTime dateTime) {
        String ans = "Here are the tasks on " + dateTime.toString().substring(0, 10) + ":\n";
        int index = 1;
        for (Task task : taskList) {
            if ((task instanceof Deadline && sameDay(((Deadline) task).getDlTime(), dateTime))
                    || (task instanceof Event && sameDay(((Event) task).geteTime(), dateTime))) {
                ans += String.format(" %d. " + task.printTask() + "\n", index);
                index++;
            }
        }
        return ans;
    }

    /**
     * print all the tasks of Today.
     *
     * @param taskList The tasklist related.
     */
    public String echoTaskToday(ArrayList<Task> taskList) {
        String ans = "Here are the tasks today:\n";
        int index = 1;
        for (Task task : taskList) {
            if ((task instanceof Deadline && sameDay(((Deadline) task).getDlTime(), LocalDateTime.now()))
                    || (task instanceof Event && sameDay(((Event) task).geteTime(), LocalDateTime.now()))) {
                ans += String.format(" %d. " + task.printTask() + "\n", index);
                index++;
            }
        }
        return ans;
    }

    /**
     * Print all the tasks that match the pattern of a find command.
     *
     * @param taskList The taskList related.
     * @param pattern  The String pattern given by find command.
     */
    public String echoPrintFindResult(ArrayList<Task> taskList, String pattern) {
        String ans = "Here are the matching tasks:\n";
        int index = 1;
        for (Task task : taskList) {
            if (task.getTaskName().contains(pattern)) {
                ans += String.format(" %d. " + task.printTask() + "\n", index);
                index++;
            }
        }
        return ans;
    }

    /**
     * Helper function that checks if 2 dateTime refers to the same day or not.
     *
     * @param dateTime1 First dateTime input
     * @param dateTime2 Second dateTime input
     * @return answer in form of boolean.
     */
    public boolean sameDay(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return ((dateTime1.getDayOfYear() == dateTime2.getDayOfYear()) && (dateTime1.getYear() == dateTime2.getYear()));
    }
}
