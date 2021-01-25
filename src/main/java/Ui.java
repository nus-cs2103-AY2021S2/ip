import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        welcomeUser();
        sc = new Scanner(System.in);
    }

    public String fullCommand() {
        return sc.nextLine();
    }

    private void welcomeUser() {
        String output = "Hello! I'm Duke\n"
                + "\tWhat can I do for you?";
        response(output);
    }

    public void response(String output) {
        String responseMsg = "\t____________________________________________________________\n"
                + "\t" + output + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(responseMsg);
    }

}
