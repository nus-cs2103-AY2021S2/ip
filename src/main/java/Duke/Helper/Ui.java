package Duke.Helper;

import java.util.ArrayList;
import Duke.Task.*;

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
    public void printAllTask(ArrayList<Task> list){
        System.out.println(LINES);
        if (list.isEmpty()){
            System.out.println("There is no task in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                System.out.println((i + 1) + ". " + task);
            }
        }
        System.out.println(LINES);
        System.out.println();
    }
}
