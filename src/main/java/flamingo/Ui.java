package flamingo;

import java.util.ArrayList;

/**
 * Contains interactions with user.
 */
public class Ui {

    public static String sayHello() {
        return "Hello! I'm Flamingo. What's on your to-do list?";
    }

    public static String sayBye() {
        return "Bye-o Flamingo!";
    }

    /**
     * Prints string when a task is added.
     *
     * @param task Task that was added.
     * @param numTasks Total number of tasks.
     * @return String to confirm that a task was added.
     */
    public static String addTask(Task task, int numTasks) {
        return "Okay, I've added this task:\n" + task.toString()
                + "\nYou now have a total of " + numTasks + " tasks.";
    }

    /**
     * Prints string when a task is marked as done.
     *
     * @param num Number of a task.
     * @param currentTask Description of the task marked as done.
     * @return String to confirm that a task was marked as done.
     */
    public static String markAsDone(int num, Task currentTask) {
        return "Okay, this task has been marked as done:\n"
                + num + ". " + currentTask.toString();
    }

    /**
     * Prints string when a task is deleted.
     *
     * @param taskToDelete Description of task deleted.
     * @param numTasks Remaining number of tasks.
     * @return Sting to confirm task deleted.
     */
    public static String deleteTask(String taskToDelete, int numTasks) {
        return "Okay, I've deleted this task:\n" + taskToDelete
                + "\nYou now have a total of " + numTasks + " tasks.";
    }

    /**
     * Prints out all the matches found.
     *
     * @param matches ArrayList of matching results.
     * @param taskNumbers ArrayList of task numbers.
     * @return String of matches found.
     */
    public static String printOutMatches(ArrayList<Task> matches, ArrayList<Integer> taskNumbers) {
        String listOfMatches = "";
        for (int j = 0; j < matches.size(); j++) {
            listOfMatches += taskNumbers.get(j) + ". " + matches.get(j) + "\n";
        }
        return "Bingo Flamingo! I've found these matches:\n"
                + listOfMatches;
    }

    public static String noMatchesFound() {
        return "Oh no Flamingo! No matches were found.";
    }

    /**
     * Returns confirmation when task is archived.
     *
     * @param task Task to be archived.
     * @param numArchivedTasks Number of archived tasks.
     * @return String of task archived.
     */
    public static String addArchivedTask(Task task, int numArchivedTasks) {
        return "Okay, I've archived this task:\n" + task.toString()
                + "\nYou have archived " + numArchivedTasks + " tasks.";
    }

    /**
     * Returns confirmation when all tasks are archived.
     *
     * @param numArchivedTasks Total number of tasks archived.
     * @return String confirming all tasks are archived.
     */
    public static String allTasksArchived(int numArchivedTasks) {
        return "All tasks have been archived!\n"
                + "You have archived " + numArchivedTasks + " tasks.";
    }
}
