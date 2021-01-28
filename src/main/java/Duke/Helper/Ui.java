package Duke.Helper;

import java.util.ArrayList;

import Duke.Constant.Constants;
import Duke.Task.Task;

/**
 * A class handles interactions with the user.
 */
public class Ui {
    public static final String LINES = "____________________________________________________________";

    /**
     * Formats the response that will be printed out to the users.
     * @param response A string that needed to be formatted before printing out.
     */
    public void printResponse(String response) {
        System.out.println(LINES);
        System.out.println(response);
        System.out.println(LINES);
        System.out.println();
    }

    /**
     * Lists all available tasks in the database.
     * @param list A list containing all the current tasks
     */
    public void printAllTask(ArrayList<Task> list) {
        System.out.println(LINES);
        if (list.isEmpty()) {
            System.out.println(Constants.EMPTY_TASK_LIST);
        } else {
            System.out.println(Constants.START_LISTING);
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                System.out.println((i + 1) + ". " + task);
            }
        }
        System.out.println(LINES);
        System.out.println();
    }

    /**
     * Prints all the matched tasks for find command.
     * @param list List of tasks that match the keyword.
     */
    public void printMatchedTask(ArrayList<Task> list) {
        System.out.println(LINES);
        if (list.isEmpty()) {
            System.out.println(Constants.FIND_FAIL);
        } else {
            System.out.println(Constants.FIND_SUCCESS);
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                System.out.println((i + 1) + ". " + task);
            }
        }
        System.out.println(LINES);
        System.out.println();
    }
}
