import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final String name = "Rawrz";
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        String border = "___";
        System.out.println(border);
    }

    public void showIntro() {
        showLine();
        System.out.println("   Hello there! I'm " + name + ", always here for you!\n   How can I help you today?");
        showLine();
    }

    public void showGoodbye() {
        showLine();
        System.out.println("   Bye! Hope to see you again! Rawrz! :)");
        showLine();
    }

    public void showError(String error) {
        showLine();
        System.out.println("   " + error);
        showLine();
    }

    public void showRemove(Task task, int noTasks) {
        showLine();
        System.out.println("   Okay! I've removed this task:");
        System.out.println("      " + task);
        System.out.println("   Now you have " +noTasks + " tasks in the list");
        showLine();
    }

    public void showList(Storage storage) {
        showLine();
        for (int i = 0; i < storage.getArrSize(); i++) {
            System.out.println("   " + (i + 1) + ". " + storage.get(i));
        }
        showLine();
    }

    public void showDone(Task task) {
        showLine();
        System.out.println("   Nice! I've marked this task as done:");
        System.out.println("      " + task);
        showLine();
    }

    public void showAdd(Task task, int noTasks) {
        showLine();
        System.out.println("   Got it! I've added this task:\n      " + task);
        System.out.println("   Now you have " + noTasks + " tasks in the list");
        showLine();
    }


}
