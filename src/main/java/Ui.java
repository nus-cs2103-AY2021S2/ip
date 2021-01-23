import java.util.Scanner;

public class Ui {

    protected Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showLoadingError() {
        System.out.println("☹ OOPS!!! There is an error in loading the file.");
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

}
