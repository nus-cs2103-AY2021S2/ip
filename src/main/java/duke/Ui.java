package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.Tasks.Task;


public class Ui {
    /** Scanner instance to scan user input*/
    private Scanner sc;

    public Ui() {
    }

    /** Scans and returns user input
     *
     * @return user input
     * */
    public String getCommand() {
        sc = new Scanner(System.in);
        String command = sc.nextLine();
        return command;
    }

    /** Scans and returns date of event or deadline
     *
     * @return date as a string
     * */
    public String getDate() {
        String date = sc.nextLine();
        return date;
    }

    /** Prints message to greet user */
    public void greetUser() {
        System.out.println("Duchess: Hello, Duchess here. What can i do for you?");
    }

    /** Prints message to say goodbye to user */
    public void sayGoodbye() {
        System.out.println("Duchess: Bye, Have an awesome day!");
        sc.close();
    }

    /** Prints message to request for date of event */
    public void requestDate() {
        System.out.println("Duchess: When will this event be?");
    }

    /** Prints message to request for deadline */
    public void requestDeadline() {
        System.out.println("Duchess: When does this have to be done by?");
    }

    /** Prints tasks in TaskList
     *
     * @param taskList of tasks to print
     * */
    public void printList(ArrayList<Task> taskList) {
        String msg = "Duchess: Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            msg += "\n" + (i + 1) + ". " + taskList.get(i);
        }
        System.out.println(msg);
    }

    /** Prints message when task is checked off successfully
     *
     * @param task to be checked
     * */
    public void checkedTask(Task task) {
        System.out.println("Duchess: Woohoo I've checked off this task:" + "\n" + task);
    }

    /** Prints message when task is successfully deleted
     *
     * @param taskList of tasks
     * @param task to be deleted
     * */
    public void deletedTask(ArrayList<Task> taskList, Task task) {
        System.out.println("Duchess: As requested, i have removed this task:" + "\n"
                + task + "\n" + "U have " + taskList.size() + " tasks in the list now :)");
    }

    /** Prints message when task is successfully added
     *
     * @param taskList of tasks
     * @param task to be added
     * */
    public void addedTask(ArrayList<Task> taskList, Task task) {
        System.out.println("Duchess: Great! I have added:" + "\n" + task + "\n" + "U have " + taskList.size()
                + " tasks in the list now :)");
    }

}
