import java.util.Scanner;

public class Ui {
    private Scanner scan;
    private static String greetings = "Hi, I am Bebong, a self-centered bot. I can't do anything for you.";
    private static String bye = "Goodbye, hope to not see you again.";
    private static String doneFail = "☹ OOPS!!! The description of a done cannot be empty.";
    private static String deleteFail = "☹ OOPS!!! The description of a delete cannot be empty.";

    public Ui() {
        Scanner scan = new Scanner(System.in);
    }

    public void printGreetings() {
        System.out.println(greetings);
    }

    public void printBye() {
        System.out.println(bye);
    }

    public void printList(int i, Task task) {
        System.out.print(i+1);
        System.out.print(".");
        System.out.println(task);
    }

    public void printDoneSuccess(Task task) {
        System.out.println("Nice I have marked this task as done!");
        System.out.println(task);
    }

    public void printDoneFail() {
        System.out.println(doneFail);
    }

    public void printDeleteSuccess(Task task, int size) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void printDeleteFail() {
        System.out.println(deleteFail);
    }

    public void printTaskFail(NoMeaningException e) {
        System.out.println(e.getMessage());
    }

    public void printTaskFinally(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }
}
