package chatbot;

import java.util.ArrayList;
import java.util.Scanner;

import chatbot.tasks.Task;

public class Ui {
    public Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void greeting() {

        System.out.println("    ---------------------------------------");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ---------------------------------------");
    }

    public void goodbye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    public void linkBreaker() {
        System.out.println("    ---------------------------------------");
    }

    public void errorLine(String message) {
        System.out.println("    Error: " + message);
    }

    public void taskListLine(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            System.out.println("    "
                    + index + ": " + tasks.get(i));
        }

        if (tasks.size() == 0) {
            System.out.println("    "
                    + "--- There is no task ---");
        }
    }

    public void printLine(String text) {
        System.out.println("    " + text);
    }

    public String nextCommand() {
        return sc.nextLine();
    }
}
