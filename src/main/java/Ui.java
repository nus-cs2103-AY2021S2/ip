import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String getUserInput() {
        return sc.nextLine();
    }

    public void printHello() {
        printHorizontalLine();
        System.out.println(Messages.MESSAGE_HELLO);
        printHorizontalLine();
    }

    public void printHorizontalLine() {
        System.out.println(Messages.MESSAGE_LINE);
    }

    public void showFeedbackToUser(CommandResult result) {
        printHorizontalLine();
        System.out.println(result.getFeedBack());
        printHorizontalLine();
    }

    public void showFeedbackToUser(String feedback) {
        printHorizontalLine();
        System.out.println(feedback);
        printHorizontalLine();
    }
}
