import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);

    }

    public void printHorizontalLine() {
        System.out.println("    ___________________________________________________________");
    }

    public void showWelcome() {
        printHorizontalLine();
        System.out.println("Hello! I'm Juke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Error loading file");
    }

    public void showError(String error) {
        System.out.println(error);
    }
}