import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String getUserInput() {
        return sc.nextLine();
    }

    public void printGetHelpMessage() {
        System.out.println("Sorry, I do not understand your command :')");
        System.out.println("If you are stuck, type 'help' to get a list of operations available");
    }

    public void printHello() {
        printHorizontalLine();
        String welcome = "Hello! My name is Lihua.\n"
                + "What can I do for you today? (=~ω~=)";
        System.out.println(welcome);
        printHorizontalLine();
    }

    public void printGoodbye() {
        printHorizontalLine();
        System.out.println("Goodbye! Hope to see you again soon! (=~ω~=)");
        printHorizontalLine();
    }

    public void printHorizontalLine() {
        System.out.println("-------------------------------------------------");
    }
}
