package justin;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class contains all the UI methods that runs in the Main Justin.java class
 *
 * @author Goh Wei Kiat aka github : mrweikiat
 * @version CS2103T AY20/21 Semester 2, Individual Project 'IP'
 */


public class Ui {

    Scanner sc;

    static String line = "-------------------------------------------------------------------------------------------";

    static String logo =

            "     ,--.                    ,--.   ,--.          \n" +
            "     |  | ,--.,--.  ,---.  ,-'  '-. `--' ,--,--,  \n" +
            ",--. |  | |  ||  | (  .-'  '-.  .-' ,--. |      \\ \n" +
            "|  '-'  / '  ''  ' .-'  `)   |  |   |  | |  ||  |  \n" +
            " `-----'   `----'  `----'    `--'   `--' `--''--'  \n";


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

    public void showWelcomeMessage() {
        printLine();
        System.out.println(logo);
        printLine();

    }

    /**
     * This method outputs the helper message when the user
     * first start the program
     *
     */

    public void showHelpMessage() {
        System.out.println("Hello I'm Justin");
        System.out.println("What can I do for you?");
        printSpace();
        System.out.println("To add a todo: use command todo<space>taskName");
        System.out.println("To add a deadline: use command deadline<space>taskName<space>/by<space>YYYY-MM-DD");
        System.out.println("To add a event: use command event<space>taskName<space>/at<space>YYYY-MM-DD<space>HH:MM");
        printSpace();
        printLine();
    }

    public void showEndMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }


    // Level 10 GUI edited
    public String showListMessage() {
        //printLine();
        return "Here are the tasks in your list:\n";
        //printSpace();
    }

    public String showDoneMessage(TaskList tasks, int listNum) {
        //printLine();

        String holder = "";

        holder += "Nice! I've marked this task as done:\n";
        holder += tasks.getList().get(listNum-1).toString() + "\n";

        return holder;

        //System.out.println("Nice! I've marked this task as done:");
        //System.out.println(tasks.getList().get(listNum-1).toString());
        //printLine();

    }

    public void printLine() {
        System.out.println(line);
    }

    public void printErrorMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    public String printList(TaskList tasks) {
        String holder = "";
        for (int i = 0; i < tasks.getList().size(); i++) {
            holder += (i+1) + ". " + tasks.getList().get(i).toString() + "\n";
        }
        //printLine();

        return holder;
    }

    public void printExceptionMessage(String m) {
        printLine();
        System.out.println(m);
        printSpace();
        printLine();
    }

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


    // Level 10 GUI

    public String welcomeMessage() {
        return "Welcome! I am Justin, your personal Timetable planner";
    }

    public String terminateMessage() {
        return "See you again soon!";
    }

    private String responseMessage = "";

    public String getResponseMessage() {
        return responseMessage;
    }

    public String editResponseMessage(String newText) {
        String holder = getResponseMessage();
        holder += newText;
        return holder;
    }


}
