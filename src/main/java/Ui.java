import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private static final String BORDER = "\t___________________________________\n";

    public Ui() {
        greetUser();
        sc = new Scanner(System.in);
    }

    public String nextCommand() {
        return sc.nextLine();
    }

    public void greetUser() {
        String output = " Hello! I'm Duke\n" + "\t What can I do for you?";
        outputMessage(output);
    }

    /**
     * Return a response from the input of user
     * @param message consists of the return output to be displayed to the user
     */
    public void outputMessage(String message) {
        System.out.println(BORDER + "\t" + message + "\n" + BORDER);
    }
}
