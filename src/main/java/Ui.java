import java.util.ArrayList;
import java.util.Scanner;

/** The user interface class is for printing Mister Duke's
 * responses onto the terminal.
 *
 */
public class Ui {
    /**
     * Prints horizontal line to partition Mister Duke's messages
     */
    public void printLine() {
        System.out.println("    _________________________________________________");
    }

    /**
     * Reads user's input
     * @return user's input as String
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            return sc.nextLine();
        }
        return "";
    }

    /**
     * Displays Mister Duke's welcome message
     */
    public String showWelcome() {
        return "Hello! Nice to meet you, I'm Mister Duke. What can I do for you today?";
    }

    /**
     * Displays Mister Duke's goodbye message
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon! :)";
    }

    /**
     * Displays number of tasks in the list
     * @param numOfTasks in the list
     */
    public String showTaskList(int numOfTasks) {
        if (numOfTasks == 1) {
            return "Now you have " + numOfTasks + " task in the list.";
        } else {
            return "Now you have " + numOfTasks + " tasks in the list.";
        }
    }

    /**
     * Informs user that the task input has been added to the list
     * @param task specified by user that needs to be added to the list
     */
    public String showTaskAdded(Task task) {
        return "Got it. I've added this task: \n" +
                "  " + task.toString();
    }

    /**
     * Indicates that the specified task has been completed
     * @param task specified by user that has been completed
     */
    public String showTaskDone(Task task) {
        return "Nice! I've marked this task as done: \n" +
                "  " + task.toString();
    }

    /**
     * Lists the tasks
     * @param tasksArray array list of tasks
     */
    public String showList(ArrayList<Task> tasksArray) {
        if (tasksArray.isEmpty()) {
            return "Your list is empty, there is nothing to do. Yay!";
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list: \n");
            for (int i = 0; i < tasksArray.size(); i++) {
//                sb.append(String.format("%d. %s", (i + 1), tasksArray.get(i).toString()) + "\n");
                sb.append(String.valueOf(i + 1) + ". " + tasksArray.get(i).toString() + "\n");
                System.out.println(sb.toString());
            }
            return sb.toString();
        }
    }

    /**
     * Informs user that the specified task has been removed from the list
     * @param tasksArray array list of tasks
     * @param commandNumber the task in the list that will be removed
     */
    public String showTaskDelete(ArrayList<Task> tasksArray, String commandNumber) {
        if (tasksArray.isEmpty()) {
            return "Oops! You have no tasks to delete.";
        } else {
            int cmdNum = Integer.parseInt(commandNumber); //strArray[1]
            String deletedTask = tasksArray.get(cmdNum - 1).toString();
            tasksArray.remove(cmdNum - 1);
            return "Noted. I've removed this task: \n" +
                    "  " + deletedTask;
        }
    }

    /**
     * Informs the user of wrong/incomplete input
     * @param e error message that specifies wrong/incomplete input
     */
    public String showDefaultError(Exception e) {
        return e.getMessage();
    }

    public String showOutOfBounds() {
        return "Oops! You don't have that many tasks";
    }

    public String showMatchingItems(ArrayList<Task> tasksArray) {
        if (tasksArray.isEmpty()) {
            return "Oh no! There are no matching tasks :(";
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list: \n");
            for (int i = 0; i < tasksArray.size(); i++) {
//                sb.append(String.format("%d. %s", (i + 1), tasksArray.get(i).toString()) + "\n");
                sb.append(String.valueOf(i + 1) + ". " + tasksArray.get(i).toString() + "\n");
            }
            return sb.toString();
        }
    }
}

