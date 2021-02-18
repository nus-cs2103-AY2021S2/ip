import java.util.List;
import java.util.List;
import java.util.Scanner;

/**
 * ui class to print ai input to user
 */
public class Ui {
    Scanner sc = new Scanner(System.in);
    String logo =
            " __        _        \n"
                    + "|  _ \\ _   | | __ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| || | || |   <  __/\n"
                    + "|_/ \\,||\\\\___|\n";
    public void sendWelcomeMessage() {
        System.out.println("Hi Im Duke, how may I help you?");
    }

    public void byeUser() {
        System.out.println("Bye bye, have a nice day! Thanks for using " + logo);
    }

    public String listenToInput() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        System.out.println("loading failed");
    }
}

