import java.util.Scanner;

public class Ui {

    private final Scanner sc = new Scanner(System.in);

    public Ui() { }

    public String readCommand() {
        return sc.nextLine();
    }

    //output at the end
    public String printEnd() {
        return "Goodnight! MouMou will go back to sleep now.";
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
