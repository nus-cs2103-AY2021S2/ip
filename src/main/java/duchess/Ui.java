package duchess;

import java.util.ArrayList;
import java.util.Scanner;

import duchess.Tasks.Task;


public class Ui {
    /** Scanner instance to scan user input*/
    private Scanner sc;

    public Ui() {
    }
    /** Prints message to greet user */
    public static String greetUser() {
        String msg = "Duchess: Hello, Duchess here. What can i do for you?";
        return msg;
    }

    /** Prints message to say goodbye to user */
    public static String sayGoodbye() {
        String msg = "Duchess: Bye, Have an awesome day!";
        return msg;
    }

    /** Prints tasks in TaskList
     *
     * * @param taskList of tasks to print
     * */
    public String printList(ArrayList<Task> taskList) {
        String msg = "Duchess: Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            msg += "\n" + (i + 1) + ". " + taskList.get(i);
        }
        return msg;
    }

    /** Prints message when task is checked off successfully
     *
     * @param task to be checked
     * */
    public String printChecked(Task task) {
        String msg = "Duchess: Woohoo I've checked off this task:" + "\n" + task;
        return msg;
    }

    /** Prints message when task is successfully deleted
     *
     * @param taskList of tasks
     * @param task to be deleted
     * */
    public String printDeleted(ArrayList<Task> taskList, Task task) {
        String msg = "Duchess: As requested, i have removed this task:" + "\n"
                + task + "\n" + "U have " + taskList.size() + " tasks in the list now :)";
        return msg;
    }

    /** Prints message when task is successfully added
     *
     * @param taskList of tasks
     * @param task to be added
     * */
    public String printAdded(ArrayList<Task> taskList, Task task) {
        String msg = "Duchess: Great! I have added:" + "\n" + task + "\n" + "U have " + taskList.size()
                + " tasks in the list now :)";
        return msg;
    }
}
