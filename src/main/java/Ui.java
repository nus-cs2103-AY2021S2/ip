import java.util.ArrayList;
import java.util.Scanner;

/**
 * In charge of interactions with the user
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Initializes a Ui object and greets the user
     */
    public Ui() {
        greetUser();
    }

    /**
     * Displays a hello message to the user
     */
    public static void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nHow can I help?\n");
    }

    /**
     * Returns line-by-line all of the tasks that matches the keyword, in string form
     * @param matchedTasksList the list of tasks that matches the keyword
     * @return a string of tasks that matches the keyword
     */
    public String showMatchedTasksList(ArrayList<String> matchedTasksList) {
        String arrayListContents = "";
        for (int i = 0; i < matchedTasksList.size(); i++) {
            arrayListContents += matchedTasksList.get(i) + "\n";
        }
        return arrayListContents;
    }

    /**
     * Prints out the message that the specified file does not exist
     */
    public void showLoadingError() {
        System.out.println("File ./savedTasks.txt not found! Creating one...\n");
    }

    public String showTaskAdded(int taskListSize) {
        return "added!\n" + "you have " + taskListSize + " tasks in your list";
    }

    /**
     * Returns a string message about the successful deletion of the specified task from the
     * task list, along with the updated task list size
     * @param taskDeleted task that had been deleted from the task list
     * @param taskListSize current updated task list size
     * @return a string that notifies the user of the deletion of the task and the updated
     * task list size
     */
    public String showTaskDeleted(Task taskDeleted, int taskListSize) {
        return "Noted. I've removed this task: " + taskDeleted + "\n"
                + "you have " + taskListSize + " tasks in your list";
    }

    public String showTaskMarkedDone(Task completedTask) {
        return "Nice! I've marked this task as done:" + completedTask;
    }

    /**
     * Returns a string farewell message
     * @return a farewell message in the form of a string
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you soon!\n";
    }

    /**
     * Returns line-by-line all of the tasks in the task list, in string form
     * @return the tasks in the task list, in string form
     */
    public String showTaskList() {
        String taskList = "Here are your tasks!\n";

        for (int i = 1; i <= TaskList.updatedTaskList.size(); i++) {
            Task task = TaskList.updatedTaskList.get(i - 1);
            taskList += i + "." + task + "\n";
        }
        return taskList;
    }
}
