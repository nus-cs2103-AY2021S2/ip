import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    void welcome() {
        System.out.println("Hello! I'm Duke. What can I do for you?\n");
    }

    void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void list(String list) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(list);
    }

    void done(String task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task + "\n");
    }

    void delete(String task, int tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %s tasks in the list.", tasks) + "\n");
    }

    void addtask(String task, int tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %s tasks in the list.", tasks) + "\n");
    }

    void showError(String errormsg) {
        System.out.println(errormsg + "\n");
    }
}
