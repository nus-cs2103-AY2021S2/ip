package duke;

import java.util.List;

public class Ui {
    protected static final String seperatorLine = "-----------------------------";

    public static void greet() {
        String welcome = "Hi, I'm Duke and I'm gonna be your assistant. Enjoy!!!";
        System.out.println(welcome);
        System.out.println();
    }

    public static void exit() {
        System.out.println(seperatorLine);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(seperatorLine);
    }

    public static void addTask(List<Task> lst) {
        System.out.println(seperatorLine);
        System.out.println("Got it. Now I have added this " +
                "task:\n" + "  " + lst.get(lst.size() - 1) + "\n" +
                "Now you have " + lst.size() + " tasks in the list.");
        System.out.println(seperatorLine);
    }

    public static void markDone(Task task) {
        System.out.println(seperatorLine);
        System.out.println("Nice! I've marked this task as done:\n" +
                "  " + task + "\n");
        System.out.println(seperatorLine);
    }

    public static void delete(List<Task> lst, Task task) {
        System.out.println(seperatorLine);
        System.out.println("Noted. I've removed this " +
                "task:\n" + "  " + task + "\n" +
                "Now you have " + lst.size() + " tasks in the list.");
        System.out.println(seperatorLine);
    }

    public static void list(List<Task> lst) {
        System.out.println(seperatorLine);
        for (int i = 0; i < lst.size(); i++) {
            System.out.println((i + 1) + ". " + lst.get(i));
        }
        System.out.println(seperatorLine);
    }

    public void find(List<Task> lst) {
        System.out.println(seperatorLine);
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println((i + 1) + ". " + lst.get(i));
        }
        System.out.println(seperatorLine);
    }

    public static void printException(DukeException err) {
        System.out.println(seperatorLine);
        System.out.println(err.getMessage());
        System.out.println(seperatorLine);
    }
}
