package flamingo;

import java.util.ArrayList;

/**
 * Contains interactions with user.
 */
public class Ui {

    /**
     * Displays introduction message for the user.
     * @return String with introduction message.
     */
    public static String sayHello() {
        return "Hello! I'm Flamingo. What's on your to-do list?\n"
                + "Type 'help' to see what commands you can use.\n";
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

    /**
     * Returns confirmation that a task has been removed from archive.
     *
     * @param taskToUnarchiveString String of the task that was unarchived.
     * @param numArchivedTasks Total number of tasks archived.
     * @return String that a task has been unarchived.
     */
    public static String unarchiveTask(String taskToUnarchiveString, int numArchivedTasks) {
        return "Okay, I've unarchived this task:\n" + taskToUnarchiveString
                + "\nYou now have " + numArchivedTasks + " tasks in the archive.";
    }

    public static String saveData() {
        return "Great! I've saved your data.";
    }

    /**
     * Shows commands that the user can try.
     * @return String of commands.
     */
    public static String showHelp() {
        return "Here are some commands you can try!\n"
                + "\nAdd a to-do: todo <description>\n"
                + "\nAdd a deadline task: deadline <description> /by <yyyy-mm-dd>\n"
                + "\nAdd an event: event <description> /at <yyyy-mm-dd>T<hh:mm>\n"
                + "\nView all tasks: list\n"
                + "\nMark a task as completed: done <index>\n"
                + "\nDelete a task: delete <index>\n"
                + "\nFind a task by keyword: find <keyword>\n"
                + "\nArchive a task: archive <index> OR archive all\n"
                + "\nView all archived tasks: archive\n"
                + "\nUnarchive a task: unarchive <index>\n"
                + "\nSave your data: save\n"
                + "\nExit the program: bye\n";
    }
}
