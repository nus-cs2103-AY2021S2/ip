package justin;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class contains all the UI methods
 * that runs in the Main Justin.java class
 *
 * @author Goh Wei Kiat aka github : mrweikiat
 * @version CS2103T AY20/21 Semester 2, Individual Project 'IP'
 */


public class Ui {

    Scanner sc;

    static String line = "-------------------------------------------------------------------------------------------";

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * This method outputs the welcome message
     * when the user first start the program
     */


    /**
     * This method outputs the helper
     * message when the user
     * first start the program
     * Then Justin will run
     *
     * @return the message to print for Justin
     */

    public String showHelpMessage() {
        String textHolder = "";

        textHolder += "To add a todo, use command: \n" +
                "eg: todo code cs2103\n";
        textHolder += "To add a deadline, use command: \n" +
                "eg: deadline fly kite /by 2020-06-10\n";
        textHolder += "To add a event, use command: \n" +
                "eg: event finish iP /at 2020-02-18 18:00\n";
        textHolder += "To find similar tasks, use eg: find my CAP\n";

        return textHolder;
    }

    public void showEndMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * This method will show the list message for the task
     * @return the UI message for task list
     */


    public String showListMessage() {
        return "Here are the tasks in your list:\n";
    }

    /**
     * This method prints the done message
     * @param tasks tasklist of tasks
     * @param num the desired task to be marked as done
     * @return the UI message for done task
     */

    public String showDoneMessage(TaskList tasks, String num) {

        String holder = "";
        int listNum = tasks.markDone(num);
        holder += "Nice! I've marked this task as done:\n";
        holder += tasks.getList().get(listNum-1).toString() + "\n";

        return holder;

    }

    public void printLine() {
        System.out.println(line);
    }

    public void printErrorMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    /**
     * This method prints the list in the tasklist
     * @param tasks containing all the tasks
     * @return a Text of tasks descriptions
     */

    public String printList(TaskList tasks) {
        String holder = "";
        for (int i = 0; i < tasks.getList().size(); i++) {
            holder += (i+1) + ". " + tasks.getList().get(i).toString() + "\n";
        }

        return holder;
    }

    public void printExceptionMessage(String m) {
        printLine();
        System.out.println(m);
        printSpace();
        printLine();
    }

    /**
     * This method prints the UI for task list
     * @param text of the task
     * @return the UI message for task list
     */

    public String printFoundTask(String text) {

        String holder = "";

        if (text.isEmpty()) {
            holder += "OOPS!! There are no such tasks in the list! Try searching using another" +
                    " key word instead!";
        } else {
            holder += "Here are the matching tasks in your list\n";
            holder = holder + text;
        }

        return holder;

    }

    public void printSpace() {
        System.out.println();
    }

    /**
     * This method prints the welcome message
     * @return the welcome message to display
     */

    public String welcomeMessage() {
        String msg = "Welcome! I am Justin,\n";
        msg += "Your personal Timetable planner\n";
        msg += "Type command 'help' to see what you can do\n";

        return msg;
    }

    /**
     * This method returns the terminating message
     * @return the terminating message after 'bye' is input
     */

    public String terminateMessage() {
        return "See you again soon!";
    }

    private String responseMessage = "";

    /**
     * This method returns the response message
     * @return response message
     */

    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * This method edit adds the response message to the text
     * @param newText of the output
     * @return a new String with response message
     */

    public String editResponseMessage(String newText) {
        String holder = getResponseMessage();
        holder += newText;
        return holder;
    }

}
