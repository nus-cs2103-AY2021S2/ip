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
     * Diplays a hello message to the user
     */
    public static void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nHow can I help?\n");
    }

    public String showMessage(String message) {
        return message;
    }

    /**
     * Displays the contents of an arraylist line by line to the user
     * @param arraylist to be displayed to the user
     * @return the contents of the arraylist
     */
    public String showArrayList(ArrayList<String> arraylist) {
        String arrayListContents = "";
        for (int i = 0; i < arraylist.size(); i++) {
            arrayListContents += arraylist.get(i) + "\n";
        }
        return arrayListContents;
    }

    /**
     * Prints out the message that the specified file does not exist
     */
    public void showLoadingError() {
        System.out.println("File ./savedTasks.txt not found! Creating one...\n");
    }

    /**
     * Displays a farewell message to the user
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you soon!\n";
    }

    /**
     * Displays line-by-line all of the tasks in the task list
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
