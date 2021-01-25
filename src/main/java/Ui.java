import java.util.Scanner;

public class Ui {

    protected Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("---------------------------------------------------------------");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showGoodBye() {
        System.out.println("Bye. Hope to see you again soon");
    }

    public void showLine() {
        System.out.println("---------------------------------------------------------------");
    }

    public void showList() {
        System.out.println("Here are the tasks in your list:");
    }

    public void showAdd(Task t, int size) {
        System.out.println("Got it. I've added this task:\n"
                + "  " + t + "\nNow you have "
                + size + " tasks in the list.");
    }

    public void showDone(Task t){
        System.out.println("Nice! I've marked this task as done:\n  " + t);
    }

    public void showDelete(Task t, int size) {
        System.out.println("Noted. I've removed this task:\n  "
                + t + "\nNow you have "
                + size + " tasks in the list.");
    }

    public void showFind() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public void showDate(String time) {
        System.out.println("Here are the tasks occurring on " + time + " in your list:");
    }

}
