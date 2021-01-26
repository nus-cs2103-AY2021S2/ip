import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void welcome() {
        System.out.println(DIVIDER);
        System.out.println("Welcome to Duke!");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
    }

    public void quit() {
        System.out.println(DIVIDER);
        System.out.println("Bye! Hope to see you again :)");
        System.out.println(DIVIDER);
    }

    public void showBorder() {
        System.out.println(DIVIDER);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}